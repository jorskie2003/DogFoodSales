package Dog.Food.Sales.DogFoodSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // make sure this is imported!
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Dog.Food.Sales.DogFoodSales.Customer;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.CUSTOMER_EMAIL = :email")
    Optional<Customer> findByCUSTOMER_EMAIL(@Param("email") String email);
    
    @Query("SELECT c FROM Customer c WHERE c.CUSTOMER_USERNAME = :username")
    Optional<Customer> findByCUSTOMER_USERNAME(@Param("username") String username);


}

