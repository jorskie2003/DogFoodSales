package Dog.Food.Sales.DogFoodSales.service;

import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.repository.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private CustomerJpaRepository customerRepository;

    // Removed unused passwordEncoder field

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Assuming the username is the email
        Customer customer = customerRepository.findByCUSTOMER_EMAIL(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                String role = customer.getRole();
        // Return a UserDetails object (Spring Security uses this)
        return User.builder()
                .username(customer.getCUSTOMER_EMAIL())
                .password(customer.getCUSTOMER_PASSWORD())  // Password is stored encrypted
                .roles(role)  // Assign roles as needed
                .build();
    }
}
