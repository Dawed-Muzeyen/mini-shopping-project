package edu.miu.cs.se.shoppingsubsystem.controller;


import edu.miu.cs.se.exceptions.shoppingcart.ShoppingCartNotFoundException;
import edu.miu.cs.se.exceptions.shoppingcartitem.ShoppingCartItemNotDeletedException;
import edu.miu.cs.se.exceptions.shoppingcartitem.ShoppingCartItemNotFoundException;
import edu.miu.cs.se.exceptions.shoppingcartitem.ShoppingCartItemNotModifiedException;
import edu.miu.cs.se.exceptions.shoppingcartitem.ShoppingCartItemNotSavedException;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCartItem;
import edu.miu.cs.se.shoppingsubsystem.service.ShoppingCartItemService;
import edu.miu.cs.se.util.ValidateShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart-item")
public class ShoppingCartItemController {
    private ShoppingCartItemService shoppingCartItemService;
    private ValidateShoppingCart validateShoppingCart;

    private Logger logger = LoggerFactory.getLogger(ShoppingCartItem.class);
    @Autowired
    public ShoppingCartItemController(ShoppingCartItemService shoppingCartItemService, ValidateShoppingCart validateShoppingCart) {
        this.shoppingCartItemService = shoppingCartItemService;
        this.validateShoppingCart = validateShoppingCart;
    }


    @PutMapping("/update/{id}")
    public ShoppingCartItem updateShoppingCartItemById(@PathVariable("id") long id, @RequestBody ShoppingCartItem shoppingCartItem) {
        logger.info("/api/v1/shopping-cart-item/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItemUpdated = shoppingCartItemService.updateShoppingCartItemById(id, shoppingCartItem);
        return shoppingCartItemUpdated.orElseThrow(() -> new ShoppingCartItemNotModifiedException("Shopping Cart Item Data is not Updated"));

    }

    @PostMapping("/save")
    public ShoppingCartItem saveShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        logger.info("/api/v1/shopping-cart-item/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItemSaved = shoppingCartItemService.saveShoppingCartItem(shoppingCartItem);
        return shoppingCartItemSaved.orElseThrow(() -> new ShoppingCartItemNotSavedException("Shopping Cart Item Data is not Saved"));


    }
    @PostMapping("/save/{order_id}")
    public ShoppingCartItem saveShoppingCartItemInfoOfOrder(@RequestBody ShoppingCartItem shoppingCartItem, @PathVariable long order_id) {
        if (!validateShoppingCart.checkValidShoppingCart(order_id))
            throw new ShoppingCartNotFoundException("There is no Order with ID " + order_id);


        logger.info("/api/v1/shopping-cart-item/save/{order_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItemSaved = shoppingCartItemService.saveShoppingCartItemInfoOfShoppingCart(shoppingCartItem, order_id);
        return shoppingCartItemSaved.orElseThrow(() -> new ShoppingCartItemNotSavedException("Shopping Cart Item Data is not Saved"));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCartItem>> getAllShoppingCartItems() {
        logger.info("/api/v1/shopping-cart-item/all api resource is being fetched");
        Optional<List<ShoppingCartItem>> shoppingCartItemList = shoppingCartItemService.getAllShoppingCartItems();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(shoppingCartItemList.orElseThrow(() -> new ShoppingCartItemNotFoundException("Shopping Cart Item Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/shopping-cart-items/{shopping_cart_id}")
    public List<ShoppingCartItem> getShoppingCartItemsByShoppingCartId(@PathVariable("shopping_cart_id") long shopping_cart_id) {
        logger.info("Checking validity of Customer with shopping cart id " + shopping_cart_id);

        if (!validateShoppingCart.checkValidShoppingCart(shopping_cart_id))
            throw new ShoppingCartNotFoundException("There is no Shopping cart data with ID " + shopping_cart_id);

        logger.info("/api/v1/shopping-cart-item/shoppingCartItems/{shopping_cart_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCartItem>> shoppingCartItemsByShoppingCartId = shoppingCartItemService.getShoppingCartItemsByShoppingCartId(shopping_cart_id);
        return shoppingCartItemsByShoppingCartId.orElseThrow(() -> new ShoppingCartItemNotFoundException("Shopping Cart Item Data with customer id " + shopping_cart_id + " is not found"));

    }

    @GetMapping("/{id}")
    public ShoppingCartItem getShoppingCartItemById(@PathVariable("id") long id) {
        logger.info("/api/v1/shopping-cart-item/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItemById = shoppingCartItemService.getShoppingCartItemById(id);
        return shoppingCartItemById.orElseThrow(() -> new ShoppingCartItemNotFoundException("Shopping Cart Item Data with id " + id + " is not found"));
         }




    @GetMapping("/quantity/{quantity}")
    public List<ShoppingCartItem> getShoppingCartItemsByQuantity(@PathVariable("quantity") long quantity) {
        logger.info("/api/v1/shopping-cart-item/quantity/{quantity} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCartItem>> shoppingCartItemByQuantity = shoppingCartItemService.getShoppingCartItemsByQuantity(quantity);
        return shoppingCartItemByQuantity.orElseThrow(() -> new ShoppingCartItemNotFoundException("Shopping Cart Item Data with quantity " + quantity + " is not found"));


    }

    @GetMapping("/total-price/{total_price}")
    public List<ShoppingCartItem> getShoppingCartItemsByTotalPrice(@PathVariable("total_price") double totalPrice) {
        logger.info("/api/v1/shopping-cart-item/total-price/{total_price} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<ShoppingCartItem>> shoppingCartItemsByTotalPrice = shoppingCartItemService.getShoppingCartItemsByTotalPrice(totalPrice);
        return shoppingCartItemsByTotalPrice.orElseThrow(() -> new ShoppingCartItemNotFoundException("Shopping Cart Item Data with the specified total price of  " + totalPrice + " is not found"));

    }

    @DeleteMapping("/delete")
    public ShoppingCartItem deleteShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        logger.info("/api/v1/shopping-cart-item/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItemDeleted = shoppingCartItemService.deleteShoppingCartItem(shoppingCartItem);
        return shoppingCartItemDeleted.orElseThrow(() -> new ShoppingCartItemNotDeletedException("Shopping Cart Item Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public ShoppingCartItem deleteShoppingCartItemById(@PathVariable("id") long id) {
        logger.info("/api/v1/shopping-cart-item/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemService.getShoppingCartItemById(id);
        if (!shoppingCartItem.isPresent() || !shoppingCartItem.isEmpty())
            throw new ShoppingCartItemNotFoundException("There is no Shopping Cart Item with id " + id + " . Hence, it is not deleted");


        Optional<ShoppingCartItem> shoppingCartItemDeleted = shoppingCartItemService.deleteShoppingCartItemById(id);
        return shoppingCartItemDeleted.orElseThrow(() -> new ShoppingCartItemNotDeletedException("Shopping Cart Item Data is not Deleted"));

    }

}