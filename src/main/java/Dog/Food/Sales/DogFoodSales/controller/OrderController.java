
package Dog.Food.Sales.DogFoodSales.controller;

import Dog.Food.Sales.DogFoodSales.*;
import Dog.Food.Sales.DogFoodSales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestParam Long customerId, @RequestBody List<OrderItemRequest> itemRequests) {
        return orderService.createOrder(customerId, itemRequests);
    }
}