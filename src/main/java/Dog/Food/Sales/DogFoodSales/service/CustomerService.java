package Dog.Food.Sales.DogFoodSales.service;



import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.repository.CustomerJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerJpaRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer saveCustomer(Customer customer) {
        return  repository.save(customer);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        customer.setCUSTOMER_NAME(customerDetails.getCUSTOMER_NAME());
        customer.setCUSTOMER_PHONENUMBER(customerDetails.getCUSTOMER_PHONENUMBER());
        customer.setCUSTOMER_EMAIL(customerDetails.getCUSTOMER_EMAIL());

        return repository.save(customer);
    }
    
    public Optional<Customer> findByEmail(String email) {
        return repository.findByCUSTOMER_EMAIL(email);
    }
}


