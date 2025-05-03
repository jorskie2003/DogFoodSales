package Dog.Food.Sales.DogFoodSales;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Dog.Food.Sales.DogFoodSales.service.ProductService;

@Component
public class FileUploadInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        try {
            productService.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
