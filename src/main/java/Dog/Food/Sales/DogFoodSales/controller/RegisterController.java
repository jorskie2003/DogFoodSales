package Dog.Food.Sales.DogFoodSales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;

import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.service.CustomerService;

@Controller
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Returns register.html
    }

    // Process form submission
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute Customer customer, Model model) {
        try {
            // Check if email already exists
            if (customerService.findByEmail(customer.getCUSTOMER_EMAIL()).isPresent()) {
                model.addAttribute("error", "emailAlreadyTaken");
                return "register"; // Stay on the page and show error
            }

            // 🔒 Encode password before saving
            customer.setCUSTOMER_PASSWORD(passwordEncoder.encode(customer.getCUSTOMER_PASSWORD()));
            customer.setRole( "USER"); // Default role for new users

            // Save new customer
            customerService.saveCustomer(customer);

            System.out.println("✅ Customer registered: " + customer.getCUSTOMER_EMAIL());

            // Redirect to login after successful registration
            return "redirect:/login";

        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            model.addAttribute("error", "An error occurred while saving the customer");
            return "register";
        }
    }
}

