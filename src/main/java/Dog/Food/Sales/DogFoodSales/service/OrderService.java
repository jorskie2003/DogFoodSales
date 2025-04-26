package Dog.Food.Sales.DogFoodSales.service;

import Dog.Food.Sales.DogFoodSales.*;
import Dog.Food.Sales.DogFoodSales.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderJpaRepository orderRepository;

    @Autowired
    private ProductJpaRepository productRepository;

    @Autowired
    private CustomerJpaRepository customerRepository;

    public Order createOrder(Long customerId, List<OrderItemRequest> itemRequests) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);

        for (OrderItemRequest itemRequest : itemRequests) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getPRODUCT_QUANTITY_STOCK() < itemRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getPRODUCT_NAME());
            }

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setTotalPrice(product.getPRODUCT_PRICE() * itemRequest.getQuantity());
            product.setPRODUCT_QUANTITY_STOCK(product.getPRODUCT_QUANTITY_STOCK() - itemRequest.getQuantity());

            order.addItem(item);
        }

        return orderRepository.save(order);
    }
}