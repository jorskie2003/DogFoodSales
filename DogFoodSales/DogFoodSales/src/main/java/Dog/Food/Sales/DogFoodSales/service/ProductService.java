package Dog.Food.Sales.DogFoodSales.service;


import Dog.Food.Sales.DogFoodSales.Product;
import Dog.Food.Sales.DogFoodSales.repository.ProductJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductJpaRepository repository;

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
}