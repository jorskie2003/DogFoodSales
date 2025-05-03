package Dog.Food.Sales.DogFoodSales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    // Show login form
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        return "login";  // Return to login.html
    }

    // Optional: Handle manual login (if you want to have custom logic, although Spring Security handles it by default)
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        // Logic to authenticate user goes here (handled by Spring Security by default)
        return "redirect:/dashboard"; // Redirect to dashboard after successful login
    }
}
