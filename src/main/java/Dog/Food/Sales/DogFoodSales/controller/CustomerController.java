package Dog.Food.Sales.DogFoodSales.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.service.CustomerService;
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

   
    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customer = service.getAllCustomers();
        model.addAttribute("customer", customer);
        return "CustomerIndex";  // Ensure "index.html" exists in templates
    }

    @GetMapping("/newCustomer")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());  // Fixed to match the model attribute
        return "CustomerCreate";  // Ensure "create.html" exists in templates
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer) {
        service.saveCustomer(customer);
        return "redirect:/customer";  
    }
    @GetMapping("/CustomerEdit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = service.getCustomerById(id);
        if (customer == null) {
            return "redirect:/customer";  
        }
        model.addAttribute("customer", customer);  
        return "CustomerEdit";  
    }


    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setCUSTOMER_ID(id); 
        service.saveCustomer(customer);
        return "redirect:/customer"; 
    }

    
    @GetMapping("/CustomerDelete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "redirect:/customer";  
    }
    
}
