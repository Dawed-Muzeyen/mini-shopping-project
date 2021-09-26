package edu.miu.cs.se.productsubsystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
   // enum Catalog{BOOK, CLOTHING};
   @Id
   @GeneratedValue
    private long id;
    private String productName;
    private long quantityAvail;
    private double unitPrice;
    private LocalDate mfgDate;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Catalog> cartItems = new ArrayList<>();

    public List<Catalog> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Catalog> cartItems) {
        this.cartItems = cartItems;
    }

    public Product() {
    }

    public Product(String productName, long quantityAvail, double unitPrice, LocalDate mfdDate, String description){
      this.productName = productName;
      this.quantityAvail = quantityAvail;
      this.unitPrice = unitPrice;
      this.mfgDate = mfdDate;
      this.description = description;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantityAvail() {
        return quantityAvail;
    }

    public void setQuantityAvail(long quantityAvail) {
        this.quantityAvail = quantityAvail;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
