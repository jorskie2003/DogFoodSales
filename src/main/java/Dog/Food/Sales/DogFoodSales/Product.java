package Dog.Food.Sales.DogFoodSales;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")

    private Long PRODUCT_ID;
    private String PRODUCT_NAME;
    private String PRODUCT_BRAND;
    private double PRODUCT_PRICE;
    private Long PRODUCT_QUANTITY;
    private Long PRODUCT_QUANTITY_STOCK;
    private String PRODUCT_DESCRIPTION;
    private String imageUrl;
    private String ImageFileName;
    
    @Override
    public String toString() {
        return "Product [PRODUCT_ID=" + PRODUCT_ID + ", PRODUCT_NAME=" + PRODUCT_NAME + ", PRODUCT_BRAND="
                + PRODUCT_BRAND + ", PRODUCT_PRICE=" + PRODUCT_PRICE + ", PRODUCT_QUANTITY=" + PRODUCT_QUANTITY
                + ", PRODUCT_QUANTITY_STOCK=" + PRODUCT_QUANTITY_STOCK + ", PRODUCT_DESCRIPTION=" + PRODUCT_DESCRIPTION
                + "]";
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageFileName() {
        return ImageFileName;
    }
    public void setImageFileName(String imageFileName) {
        ImageFileName = imageFileName;
    }
    public Long getPRODUCT_ID() {
        return PRODUCT_ID;
    }
    public void setPRODUCT_ID(Long pRODUCT_ID) {
        PRODUCT_ID = pRODUCT_ID;
    }
    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }
    public void setPRODUCT_NAME(String pRODUCT_NAME) {
        PRODUCT_NAME = pRODUCT_NAME;
    }
    public String getPRODUCT_BRAND() {
        return PRODUCT_BRAND;
    }
    public void setPRODUCT_BRAND(String pRODUCT_BRAND) {
        PRODUCT_BRAND = pRODUCT_BRAND;
    }
    public double getPRODUCT_PRICE() {
        return PRODUCT_PRICE;
    }
    public void setPRODUCT_PRICE(double pRODUCT_PRICE) {
        this.PRODUCT_PRICE = pRODUCT_PRICE;
    }
    public Long getPRODUCT_QUANTITY() {
        return PRODUCT_QUANTITY;
    }
    public void setPRODUCT_QUANTITY(Long pRODUCT_QUANTITY) {
        PRODUCT_QUANTITY = pRODUCT_QUANTITY;
    }
    public Long getPRODUCT_QUANTITY_STOCK() {
        return PRODUCT_QUANTITY_STOCK;
    }
    public void setPRODUCT_QUANTITY_STOCK(Long pRODUCT_QUANTITY_STOCK) {
        PRODUCT_QUANTITY_STOCK = pRODUCT_QUANTITY_STOCK;
    }
    public String getPRODUCT_DESCRIPTION() {
        return PRODUCT_DESCRIPTION;
    }
    public void setPRODUCT_DESCRIPTION(String pRODUCT_DESCRIPTION) {
        PRODUCT_DESCRIPTION = pRODUCT_DESCRIPTION;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
                Product product = (Product) o;
                return Objects.equals(PRODUCT_ID, product.PRODUCT_ID);
}

    @Override
    public int hashCode() {
        return Objects.hash(PRODUCT_ID);
}

    public Product(){
        
    }
}
