
package Dog.Food.Sales.DogFoodSales.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Dog.Food.Sales.DogFoodSales.Product;
import Dog.Food.Sales.DogFoodSales.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String listProducts(Model model) {
    List<Product> products = service.getAllProducts();
    model.addAttribute("products", products);
    model.addAttribute("addedToCart", false);

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
    public String saveProducts(@ModelAttribute Product product,@RequestParam("imageFile") MultipartFile file) throws IOException  {
        service.saveProductWithImage(product, file);
        return "redirect:/products";  
    }
    @GetMapping("/admin-editproducts/{id}")
    public String showAdminEditForm(@PathVariable Long id, Model model) {
        Product product = service.getProductById(id);
        
        if (product == null) { 
            model.addAttribute("error", "Product not found.");
            return "admin-editproducts";  
        }
        
        model.addAttribute("product", product); 
        return "admin-editproducts"; 
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("imageFile") MultipartFile file) throws IOException {
        product.setPRODUCT_ID(id); 
        service.saveProductWithImage(product, file);
        return "redirect:/products"; 
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/products";  
    }
}


