package Dog.Food.Sales.DogFoodSales.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Dog.Food.Sales.DogFoodSales.Product;
import Dog.Food.Sales.DogFoodSales.repository.ProductJpaRepository;

@Service
public class ProductService {

    @Autowired
    private ProductJpaRepository repository;

    private final Path root = Paths.get("uploads");

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return  repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setPRODUCT_NAME(productDetails.getPRODUCT_NAME());
        product.setPRODUCT_BRAND(productDetails.getPRODUCT_BRAND());
        product.setPRODUCT_PRICE(productDetails.getPRODUCT_PRICE());
        product.setPRODUCT_QUANTITY(productDetails.getPRODUCT_QUANTITY());
        product.setPRODUCT_QUANTITY_STOCK(productDetails.getPRODUCT_QUANTITY_STOCK());
        product.setPRODUCT_DESCRIPTION(productDetails.getPRODUCT_DESCRIPTION());

        return repository.save(product);
    }
    public void saveProductWithImage(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
            product.setImageFileName(fileName);
            product.setImageUrl("/uploads/" + fileName);
        }
        saveProduct(product);
    }

    public void init() throws IOException {
        Files.createDirectories(root);
    }
}