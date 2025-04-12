package Dog.Food.Sales.DogFoodSales;
import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")

    private Long CUSTOMER_ID;
    private String CUSTOMER_NAME;
    private Long CUSTOMER_PHONENUMBER;
    private String CUSTOMER_EMAIL;

    
    @Override
    public String toString() {
        return "Customer [CUSTOMER_ID=" + CUSTOMER_ID + ", CUSTOMER_NAME=" + CUSTOMER_NAME + ", CUSTOMER_PHONENUMBER="
                + CUSTOMER_PHONENUMBER + ", CUSTOMER_EMAIL=" + CUSTOMER_EMAIL + "]";
                
    }
    public Long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }
    public void setCUSTOMER_ID(Long cUSTOMER_ID) {
        CUSTOMER_ID = cUSTOMER_ID;
    }
    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }
    public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
        CUSTOMER_NAME = cUSTOMER_NAME;
    }
    public Long getCUSTOMER_PHONENUMBER() {
        return CUSTOMER_PHONENUMBER;
    }
    public void setCUSTOMER_PHONENUMBER(Long cUSTOMER_PHONENUMBER) {
        CUSTOMER_PHONENUMBER = cUSTOMER_PHONENUMBER;
    }
    public String getCUSTOMER_EMAIL() {
        return CUSTOMER_EMAIL;
    }
    public void setCUSTOMER_EMAIL(String cUSTOMER_EMAIL) {
        CUSTOMER_EMAIL = cUSTOMER_EMAIL;
    }
    public Customer(){
    }


}