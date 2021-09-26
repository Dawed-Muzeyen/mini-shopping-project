package edu.miu.cs.se.customersubsystem.model;


import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Customer{


    //private String aboutMe;

   // @Min(value = 18, message = "Age should not be less than 18")
   // @Max(value = 150, message = "Age should not be greater than 150")
   // private int age;




    @Id
    @GeneratedValue
    private long id;
    @Size(min = 5, max = 10, message
            = "first name must be between 5 and 10 characters")
    @NotBlank
    @NotEmpty(message = "Name may not be empty")
    @Column(nullable = false)
    private String firstName;
    @NotBlank
    @NotEmpty(message = "Name may not be empty")
    @Column(nullable = false)
    private String lastName;
    @NotBlank
    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street1", column=@Column(name="ship_street1")),
            @AttributeOverride(name="street2", column=@Column(name="ship_street2")),
            @AttributeOverride(name="city", column=@Column(name="ship_city")),
            @AttributeOverride(name="state", column=@Column(name="ship_state")),
            @AttributeOverride(name= "zipcode", column=@Column(name="ship_zip"))
    })
    private Address shipping;
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street1", column=@Column(name="bill_street1")),
            @AttributeOverride(name="street2", column=@Column(name="bill_street2")),
            @AttributeOverride(name="city", column=@Column(name="bill_city")),
            @AttributeOverride(name="state", column=@Column(name="bill_state")),
            @AttributeOverride(name= "zipcode", column=@Column(name="bill_zip"))
    })
    private Address billing;

     @OneToMany(cascade=CascadeType.ALL)
     @JoinColumn(name = "customer_id")
     private List<CreditCard> cardList = new ArrayList<>() ;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<ShoppingCart> shoppingCart = new ArrayList<>();
   public Customer(){}
    public Customer( String firstName, String lastName, Address shipping, Address billing, String email, String phoneNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.shipping = shipping;
        this.billing = billing;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCardList(List<CreditCard> cardList) {
     this.cardList = cardList;
    }

    public List<CreditCard> getCardList() {

        return cardList;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}