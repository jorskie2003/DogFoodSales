package Dog.Food.Sales.DogFoodSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Dog.Food.Sales.DogFoodSales.Order;
@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by customer ID or order status
    
}
