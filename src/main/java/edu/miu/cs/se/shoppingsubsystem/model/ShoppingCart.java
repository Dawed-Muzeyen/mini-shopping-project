package edu.miu.cs.se.shoppingsubsystem.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime orderDateTime;
    private double totalPrice;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private List<ShoppingCartItem> cartItems = new ArrayList<>();

    public ShoppingCart() {
        double t = 0;
        for (ShoppingCartItem item : cartItems)
            t += (item.getQuantity() * item.getTotalPrice());

        this.totalPrice = t;
        this.orderDateTime = LocalDateTime.now();
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public List<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
