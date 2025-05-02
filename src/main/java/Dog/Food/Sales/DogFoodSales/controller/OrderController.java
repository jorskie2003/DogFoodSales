package Dog.Food.Sales.DogFoodSales.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Dog.Food.Sales.DogFoodSales.CartItem;
import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.Order;
import Dog.Food.Sales.DogFoodSales.OrderItem;
import Dog.Food.Sales.DogFoodSales.repository.OrderJpaRepository;
import Dog.Food.Sales.DogFoodSales.repository.ProductJpaRepository;
import Dog.Food.Sales.DogFoodSales.service.CartService;
import Dog.Food.Sales.DogFoodSales.service.CustomerService;
import Dog.Food.Sales.DogFoodSales.service.ProductService;

@Controller
public class OrderController {
    @Autowired
    private OrderJpaRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductJpaRepository productRepository;
    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/checkout")
    public String checkout(Model model) {
        // Get cart items and total price
        List<CartItem> cartItems = cartService.getCartItems();
        double totalPrice = cartService.calculateTotalPrice();
    
        // Pass data to the frontend
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
    
        return "checkout"; // Return the checkout.html page
    }
    @GetMapping("/cart")
    public String viewCart(Model model) {    
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        return "cart"; // Displays cart.html
    }
    @PostMapping("/update-cart")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }
    @PostMapping("/remove-cart-item")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }
    @PostMapping("/add-to-cart")
        public String addToCart(@RequestParam Long productId, RedirectAttributes redirectAttributes) {
            productRepository.findById(productId).ifPresent(product -> {
            cartService.addToCart(product, 1);
            redirectAttributes.addFlashAttribute("addedProduct", product);
    });
    return "redirect:/products"; // or your shop page mapping
}
    @PostMapping("/place-order")
    public String placeOrder(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String email,
        @RequestParam String phoneNumber,
        @RequestParam String address,
        @RequestParam String city,
        @RequestParam String region,
        @RequestParam String zipCode,
        @RequestParam(required = false) String notes,
        @RequestParam String paymentMethod,
        Principal principal,  // to get logged-in user
        Model model) {

    // 1. Get logged-in customer (adjust according to your security setup)
    Optional<Customer> optionalCustomer = customerService.findByEmail(principal.getName());
    if (optionalCustomer.isEmpty()) {
        model.addAttribute("error", "Customer not found or not logged in.");
        return "checkout";
    }
    Customer customer = optionalCustomer.get();
    // 2. Create new Order and set customer & date
    Order order = new Order();
    order.setCustomer(customer);
    order.setOrderDate(LocalDateTime.now());

    // 3. Create OrderItems from CartService's cart items
    List<CartItem> cartItems = cartService.getCartItems();
    if (cartItems.isEmpty()) {
        model.addAttribute("error", "Your cart is empty.");
        return "checkout";
    }

    for (CartItem cartItem : cartItems) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setTotalPrice(cartItem.getProduct().getPRODUCT_PRICE());
        order.addItem(orderItem);
        order.setShippingAddress(address);
        order.setShippingCity(city);
        order.setShippingRegion(region);
        order.setShippingZip(zipCode);
        order.setShippingPhone(phoneNumber);
    }

    orderRepository.save(order);

    cartService.clearCart();

    model.addAttribute("order", order);

    return "order-success";  // your order confirmation page
}
}
