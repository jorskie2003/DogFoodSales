
package Dog.Food.Sales.DogFoodSales.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return "index";  // Ensure "index.html" exists in templates
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


