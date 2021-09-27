package edu.miu.cs.se.shoppingsubsystem.controller;


import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotModifiedException;
import edu.miu.cs.se.exceptions.shoppingcart.ShoppingCartNotDeletedException;
import edu.miu.cs.se.exceptions.shoppingcart.ShoppingCartNotFoundException;
import edu.miu.cs.se.exceptions.shoppingcart.ShoppingCartNotSavedException;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
import edu.miu.cs.se.shoppingsubsystem.service.ShoppingCartService;
import edu.miu.cs.se.util.ValidateCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shoppingCart")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private ValidateCustomer validateCustomer;


    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ValidateCustomer validateCustomer) {
        this.shoppingCartService = shoppingCartService;
        this.validateCustomer = validateCustomer;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);


    @PutMapping("/update/{id}")
    public ShoppingCart updateShoppingCartById(@PathVariable("id") long id, @RequestBody ShoppingCart shoppingCart) {
        logger.info("/api/v1/shopping-cart/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCartUpdated = shoppingCartService.updateShoppingCartById(id, shoppingCart);
        return shoppingCartUpdated.orElseThrow(() -> new OrderNotModifiedException("Shopping Cart Data is not Updated"));
    }


    @PostMapping("/save")
    public ShoppingCart saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        logger.info("/api/v1/shopping-cart/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCartSaved = shoppingCartService.saveShoppingCart(shoppingCart);
        return shoppingCartSaved.orElseThrow(() -> new ShoppingCartNotSavedException("Shopping Cart Data is not Saved"));


    }

    @PostMapping("/save/{customer_id}")
    public ShoppingCart saveShoppingCartInfoOfCustomer(@RequestBody ShoppingCart shoppingCart, @PathVariable long customer_id) {
        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);


        logger.info("/api/v1/shopping-cart/save/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCartSaved = shoppingCartService.saveShoppingCartInfoOfCustomer(shoppingCart, customer_id);
        return shoppingCartSaved.orElseThrow(() -> new ShoppingCartNotSavedException("Shopping Cart Data is not Saved"));
    }


    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        logger.info("/api/v1/shopping-cart/all api resource is being fetched");
        Optional<List<ShoppingCart>> shoppingCartList = shoppingCartService.getAllShoppingCarts();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(shoppingCartList.orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/shopping-carts/{customer_id}")
    public List<ShoppingCart> getShoppingCartsByCustomerId(@PathVariable("customer_id") long customer_id) {
        logger.info("Checking validity of Customer with customer id " + customer_id);

        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);

        logger.info("/api/v1/shopping-cart/shoppingCarts/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCart>> shoppingCartsByCustomerId = shoppingCartService.getShoppingCartsByCustomerId(customer_id);
        return shoppingCartsByCustomerId.orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart Data with customer id " + customer_id + " is not found"));
    }

    @GetMapping("/{id}")
    public ShoppingCart getShoppingCartById(@PathVariable("id") long id) {
        logger.info("/api/v1/shopping-cart/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCartById = shoppingCartService.getShoppingCartById(id);
        return shoppingCartById.orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart Data with id " + id + " is not found"));
    }


    @GetMapping("/shoppingCart-date-time/{shopping_cart_date_time}")
    public List<ShoppingCart> getShoppingCartsByShoppingCartDateTime(@PathVariable(value = "shopping_cart_date_time") LocalDateTime shoppingCartDateTime) {
        logger.info("/api/v1/shopping-cart/shopping-cart-date-time/{shopping_cart_date_time} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCart>> shoppingCartByShoppingCartDateTime = shoppingCartService.getShoppingCartsByOrderDateTime(shoppingCartDateTime);
        return shoppingCartByShoppingCartDateTime.orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart Data with the specified date and time of  " + shoppingCartDateTime + " is not found"));
    }

    @GetMapping("/total-price/{total_price}")
    public List<ShoppingCart> getShoppingCartsByTotalPrice(@PathVariable(value = "total_price") double totalPrice) {
        logger.info("/api/v1/shopping-cart/total-price/{total_price} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCart>> shoppingCartsByTotalPrice = shoppingCartService.getShoppingCartsByTotalPrice(totalPrice);
        return shoppingCartsByTotalPrice.orElseThrow(() -> new ShoppingCartNotFoundException("Shopping Cart Data with the specified date and time of  " + totalPrice + " is not found"));


    }

    @DeleteMapping("/delete")
    public ShoppingCart deleteShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        logger.info("/api/v1/shopping-cart/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCartDeleted = shoppingCartService.deleteShoppingCart(shoppingCart);
        return shoppingCartDeleted.orElseThrow(() -> new ShoppingCartNotDeletedException("Shopping Cart Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public ShoppingCart deleteShoppingCartById(@PathVariable("id") long id) {
        logger.info("/api/v1/shopping-cart/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCartById(id);
        if (!shoppingCart.isPresent() || !shoppingCart.isEmpty())
            throw new ShoppingCartNotFoundException("There is no Shopping Cart with id " + id + " . Hence, it is not deleted");


        Optional<ShoppingCart> shoppingCartDeleted = shoppingCartService.deleteShoppingCartById(id);
        return shoppingCartDeleted.orElseThrow(() -> new ShoppingCartNotDeletedException("Shopping Cart Data is not Deleted"));
    }
}
