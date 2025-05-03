package Dog.Food.Sales.DogFoodSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Dog.Food.Sales.DogFoodSales.OrderItem;
@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
   
    
}

