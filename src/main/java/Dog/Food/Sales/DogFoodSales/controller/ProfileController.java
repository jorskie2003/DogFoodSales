package Dog.Food.Sales.DogFoodSales.controller;

import Dog.Food.Sales.DogFoodSales.Customer;
import Dog.Food.Sales.DogFoodSales.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private CustomerService customerService;

    // Display profile page
    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // redirect to login if not authenticated
        }

        String email = principal.getName(); // assumes username == email
        Optional<Customer> customerOpt = customerService.findByEmail(email);

        if (!customerOpt.isPresent()) {
            // Optionally, handle the case where the customer is not found
            return "redirect:/login"; // or show an error page
        }

        model.addAttribute("customer", customerOpt.get());
        return "profile"; // profile.html
    }

    // Handle profile update
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Customer customer, Principal principal) {
        String email = principal.getName();
        Optional<Customer> existingOpt = customerService.findByEmail(email);

        if (!existingOpt.isPresent()) {
            return "redirect:/login"; // or handle error appropriately
        }

        Customer existing = existingOpt.get();

        // Update only allowed fields
        existing.setCUSTOMER_NAME(customer.getCUSTOMER_NAME());
        existing.setCUSTOMER_PHONENUMBER(customer.getCUSTOMER_PHONENUMBER());
        existing.setCUSTOMER_EMAIL(customer.getCUSTOMER_EMAIL());

        customerService.saveCustomer(existing);

        return "redirect:/profile";
    }
}
