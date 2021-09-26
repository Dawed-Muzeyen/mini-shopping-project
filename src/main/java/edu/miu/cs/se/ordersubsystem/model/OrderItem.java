package edu.miu.cs.se.ordersubsystem.model;


import edu.miu.cs.se.productsubsystem.model.Product;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private long id;
    private long quantity;
    private double totalPrice;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    public OrderItem(long quantity, double totalPrice, Product product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    public OrderItem() {
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
