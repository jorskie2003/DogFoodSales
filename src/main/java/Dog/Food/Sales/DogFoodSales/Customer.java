package Dog.Food.Sales.DogFoodSales;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long CUSTOMER_ID;

    @Column(name = "CUSTOMER_NAME")
    private String CUSTOMER_NAME;

    @Column(name = "CUSTOMER_PHONENUMBER")
    private Long CUSTOMER_PHONENUMBER;

    @Column(name = "CUSTOMER_EMAIL")
    private String CUSTOMER_EMAIL;

    @Column(name = "CUSTOMER_PASSWORD")
    private String CUSTOMER_PASSWORD;

    @Column(name = "CUSTOMER_USERNAME")
    private String CUSTOMER_USERNAME;

    @Column(name = "CUSTOMER_ADDRESS")
    private String CUSTOMER_ADDRESS;

    @Column(name = "CUSTOMER_CITY")
    private String CUSTOMER_CITY;

    @Column(name = "ROLE") 
    private String role;

    public Customer() {}

    // Getters and Setters
    public Long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(Long CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public Long getCUSTOMER_PHONENUMBER() {
        return CUSTOMER_PHONENUMBER;
    }

    public void setCUSTOMER_PHONENUMBER(Long CUSTOMER_PHONENUMBER) {
        this.CUSTOMER_PHONENUMBER = CUSTOMER_PHONENUMBER;
    }

    public String getCUSTOMER_EMAIL() {
        return CUSTOMER_EMAIL;
    }

    public void setCUSTOMER_EMAIL(String CUSTOMER_EMAIL) {
        this.CUSTOMER_EMAIL = CUSTOMER_EMAIL;
    }

    public String getCUSTOMER_PASSWORD() {
        return CUSTOMER_PASSWORD;
    }

    public void setCUSTOMER_PASSWORD(String CUSTOMER_PASSWORD) {
        this.CUSTOMER_PASSWORD = CUSTOMER_PASSWORD;
    }

    public String getCUSTOMER_USERNAME() {
        return CUSTOMER_USERNAME;
    }

    public void setCUSTOMER_USERNAME(String CUSTOMER_USERNAME) {
        this.CUSTOMER_USERNAME = CUSTOMER_USERNAME;
    }

    public String getCUSTOMER_ADDRESS() {
        return CUSTOMER_ADDRESS;
    }

    public void setCUSTOMER_ADDRESS(String CUSTOMER_ADDRESS) {
        this.CUSTOMER_ADDRESS = CUSTOMER_ADDRESS;
    }

    public String getCUSTOMER_CITY() {
        return CUSTOMER_CITY;
    }

    public void setCUSTOMER_CITY(String CUSTOMER_CITY) {
        this.CUSTOMER_CITY = CUSTOMER_CITY;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer [CUSTOMER_ID=" + CUSTOMER_ID +
                ", CUSTOMER_NAME=" + CUSTOMER_NAME +
                ", CUSTOMER_PHONENUMBER=" + CUSTOMER_PHONENUMBER +
                ", CUSTOMER_EMAIL=" + CUSTOMER_EMAIL +
                ", CUSTOMER_PASSWORD=" + CUSTOMER_PASSWORD +
                ", CUSTOMER_USERNAME=" + CUSTOMER_USERNAME +
                ", CUSTOMER_ADDRESS=" + CUSTOMER_ADDRESS +
                ", CUSTOMER_CITY=" + CUSTOMER_CITY +
                ", role=" + role + "]";
    }
}
