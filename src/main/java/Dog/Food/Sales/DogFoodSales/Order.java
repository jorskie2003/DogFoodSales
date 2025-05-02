package Dog.Food.Sales.DogFoodSales;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;

    @Column(name = "SHIPPING_CITY")
    private String shippingCity;

    @Column(name = "SHIPPING_REGION")
    private String shippingRegion;

    @Column(name = "SHIPPING_ZIP")
    private String shippingZip;

    @Column(name = "SHIPPING_PHONE")
    private String shippingPhone;

    public Order() {}

    
    // Add helper method
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public String getShippingCity() {
        return shippingCity;
    }
    public String getShippingRegion() {
        return shippingRegion;
    }
    public String getShippingZip() {
        return shippingZip;
    }
    public String getShippingPhone() {
        return shippingPhone;
    }
    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }
    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }
    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }
    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }
}