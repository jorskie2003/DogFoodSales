package Dog.Food.Sales.DogFoodSales.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import Dog.Food.Sales.DogFoodSales.CartItem;
import Dog.Food.Sales.DogFoodSales.Product;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private final Map<Product, Integer> cartItems = new HashMap<>();

    // Add product to cart
    public void addToCart(Product product, int quantity) {
        System.out.println("Adding to cart: " + product.getPRODUCT_NAME());
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        System.out.println("Cart now has: " + cartItems.size() + " items.");
   }

   public void updateQuantity(long productId, int quantity) {
    Product productToUpdate = findProductById(productId);
    if (productToUpdate != null) {
        if (quantity <= 0) {
            cartItems.remove(productToUpdate); // Remove if quantity is 0 or negative
        } else {
            cartItems.put(productToUpdate, quantity);
        }
    }
}
public List<CartItem> getCartItems() {
    System.out.println("Returning cart items: " + cartItems.size());
    return new ArrayList<>(cartItems.entrySet().stream()
        .map(entry -> new CartItem(entry.getKey(), entry.getValue()))
        .toList());
}

// Remove product from cart
public void removeItem(long productId) {
    Product productToRemove = findProductById(productId);
    if (productToRemove != null) {
        cartItems.remove(productToRemove);
    }
}

// Calculate total price of items in cart
public double calculateTotalPrice() {
    return cartItems.entrySet().stream()
        .mapToDouble(entry -> entry.getKey().getPRODUCT_PRICE() * entry.getValue())
        .sum();
    }

// Helper method to find a product by ID in the cart
    private Product findProductById(long productId) {
        return cartItems.keySet().stream()
            .filter(product -> product.getPRODUCT_ID() == productId)
            .findFirst()
            .orElse(null);
    }

    // Clear the entire cart (useful after checkout)
    public void clearCart() {
        cartItems.clear();
    }

    // Get total number of items in cart (for display in navigation)
    public int getTotalItems() {
        return cartItems.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}