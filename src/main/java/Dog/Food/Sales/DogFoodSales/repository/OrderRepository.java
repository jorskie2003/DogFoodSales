package Dog.Food.Sales.DogFoodSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Dog.Food.Sales.DogFoodSales.Order;
@Repository
public interface  OrderRepository extends JpaRepository<Order, Long> {
    
}
