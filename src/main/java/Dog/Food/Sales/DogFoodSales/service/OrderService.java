package Dog.Food.Sales.DogFoodSales.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.Order;
import Dog.Food.Sales.DogFoodSales.OrderItem;
import Dog.Food.Sales.DogFoodSales.OrderItemRequest;
import Dog.Food.Sales.DogFoodSales.Product;
import Dog.Food.Sales.DogFoodSales.repository.CustomerJpaRepository;
import Dog.Food.Sales.DogFoodSales.repository.OrderJpaRepository;
import Dog.Food.Sales.DogFoodSales.repository.OrderItemJpaRepository;
import Dog.Food.Sales.DogFoodSales.repository.ProductJpaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderJpaRepository orderRepository;

    @Autowired
    private ProductJpaRepository productRepository;

    @Autowired
    private CustomerJpaRepository customerRepository;

    @Autowired
    private OrderItemJpaRepository orderItemRepository;

    public Order saveOrder(Order order) {
        // Ensure order date is set
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        // Save the order with its items
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public boolean addItemToCart(Customer customer, OrderItemRequest itemRequest) {
        try {
            // 1. Retrieve Product
            Optional<Product> productOptional = productRepository.findById(itemRequest.getProductId());
            if (productOptional.isEmpty()) {
                logger.warn("Product not found with ID: {}", itemRequest.getProductId());
                return false;
            }
            Product product = productOptional.get();

            // 2. Get or Create Cart (Order)
            Order order = getCartForCustomer(customer);
            if (order == null) {
                order = new Order();
                order.setCustomer(customer);
                order.setItems(new ArrayList<>());
                order = orderRepository.save(order);
            }

            // 3. Check if Product Exists in Cart
            OrderItem existingItem = null;
            for (OrderItem item : order.getItems()) {
                if (item.getProduct().getPRODUCT_ID().equals(product.getPRODUCT_ID())) {
                    existingItem = item;
                    break;
                }
            }

            // 4. Update Quantity or Create New OrderItem
            int quantityToAdd = itemRequest.getQuantity();
            if (existingItem != null) {
                // Update Quantity
                int newQuantity = existingItem.getQuantity() + quantityToAdd;
                existingItem.setQuantity(newQuantity);
                existingItem.setTotalPrice(product.getPRODUCT_PRICE() * newQuantity);
                orderItemRepository.save(existingItem);
            } else {
                // Create New OrderItem
                OrderItem newItem = new OrderItem();
                newItem.setOrder(order);
                newItem.setProduct(product);
                newItem.setQuantity(quantityToAdd);
                newItem.setTotalPrice(product.getPRODUCT_PRICE() * quantityToAdd);
                newItem = orderItemRepository.save(newItem);
                order.addItem(newItem);
            }

            orderRepository.save(order);
            return true;

        } catch (Exception e) {
            logger.error("Error adding item to cart", e);
            return false;
        }
    }

    public Order getCartForCustomer(Customer customer) {
        // You might want to add a status to the Order entity to differentiate between
        // carts and completed orders. For now, we'll just retrieve the most recent
        // order.
        List<Order> orders = orderRepository.findByCustomer(customer);
        if (orders != null && !orders.isEmpty()) {
            return orders.get(orders.size() - 1); // Get the most recent order
        }
        return null;
    }

    // Method for cart checkout - creates an order from session cart items
    @Transactional
    public Order createOrder(List<OrderItem> cartItems, Customer customer) {
        // Create new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>()); // Initialize items list

        // Add cart items to order using the helper method in Order class
        for (OrderItem cartItem : cartItems) {
            // Re-attach product to avoid detached entity issues
            Product product = productRepository.findById(cartItem.getProduct().getPRODUCT_ID())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            // Calculate price based on current product price
            double itemTotalPrice = product.getPRODUCT_PRICE() * cartItem.getQuantity();

            orderItem.setTotalPrice(itemTotalPrice);

            // Use the helper method from Order class
            order.addItem(orderItem);

            // Update product stock
            product.setPRODUCT_QUANTITY_STOCK(product.getPRODUCT_QUANTITY_STOCK() - cartItem.getQuantity());
            productRepository.save(product);
        }

        // Save the complete order
        return saveOrder(order);
    }

    // Original method remains for backwards compatibility
    @Transactional
    public Order createOrder(Long customerId, List<OrderItemRequest> itemRequests) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>()); // Initialize items list

        for (OrderItemRequest itemRequest : itemRequests) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());

            orderItem.setTotalPrice(product.getPRODUCT_PRICE() * itemRequest.getQuantity());

            order.addItem(orderItem);

            // Update product stock
            product.setPRODUCT_QUANTITY_STOCK(product.getPRODUCT_QUANTITY_STOCK() - itemRequest.getQuantity());
            productRepository.save(product);
        }

        return saveOrder(order);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
