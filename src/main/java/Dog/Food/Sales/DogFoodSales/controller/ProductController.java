
package Dog.Food.Sales.DogFoodSales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Dog.Food.Sales.DogFoodSales.Product;
import Dog.Food.Sales.DogFoodSales.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

   
    @GetMapping
    public String listProducts(Model model) {
    List<Product> products = service.getAllProducts();
    model.addAttribute("products", products);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
        return "admin-products"; // admin HTML view
    } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
        return "shop"; // user HTML view
    }

    return "access-denied"; // fallback view if no role matches
}

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());  // Fixed to match the model attribute
        return "create";  // Ensure "create.html" exists in templates
    }

    @PostMapping
    public String saveProducts(@ModelAttribute Product product) {
        service.saveProduct(product);
        return "redirect:/products";  
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = service.getProductById(id);
        if (product == null) {
            return "redirect:/products";  
        }
        model.addAttribute("product", product);  
        return "edit";  
    }


    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setPRODUCT_ID(id); 
        service.saveProduct(product);
        return "redirect:/products"; 
    }

    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/products";  
    }
}


