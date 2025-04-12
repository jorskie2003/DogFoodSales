package Dog.Food.Sales.DogFoodSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Dog.Food.Sales.DogFoodSales.Customer;
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
