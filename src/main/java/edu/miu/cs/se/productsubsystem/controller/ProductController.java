package edu.miu.cs.se.productsubsystem.controller;

import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotModifiedException;
import edu.miu.cs.se.exceptions.orderitem.OrderItemNotFoundException;
import edu.miu.cs.se.exceptions.product.ProductNotDeletedException;
import edu.miu.cs.se.exceptions.product.ProductNotFoundException;
import edu.miu.cs.se.exceptions.product.ProductNotSavedException;
import edu.miu.cs.se.exceptions.shoppingcartitem.ShoppingCartItemNotFoundException;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.service.ProductService;
import edu.miu.cs.se.util.ValidateOrderItem;
import edu.miu.cs.se.util.ValidateShoppingCartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/Product")
public class ProductController {

    private ProductService productService;
    private ValidateShoppingCartItem validateShoppingCartItem;
    private ValidateOrderItem validateOrderItem;

    @Autowired
    public ProductController(ProductService productService, ValidateShoppingCartItem validateShoppingCartItem, ValidateOrderItem validateOrderItem) {
        this.productService = productService;
        this.validateShoppingCartItem = validateShoppingCartItem;
        this.validateOrderItem = validateOrderItem;
    }

    private Logger logger = LoggerFactory.getLogger(ProductController.class);


    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable("id") long id, @RequestBody Product product) {
        logger.info("/api/v1/product/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productUpdated = productService.updateProductById(id, product);
        return productUpdated.orElseThrow(() -> new OrderNotModifiedException("Product Data is not Updated"));
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        logger.info("/api/v1/product/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productSaved = productService.saveProduct(product);
        return productSaved.orElseThrow(() -> new ProductNotSavedException("Product Data is not Saved"));
    }
    @PostMapping("/save/{order_item_id}")
    public Product saveProductInfoOfOrderItem(@RequestBody Product product, @PathVariable long order_item_id) {
        if (!validateOrderItem.checkValidOrderItem(order_item_id))
            throw new OrderItemNotFoundException("There is no Order Item with ID " + order_item_id);


        logger.info("/api/v1/product/save/{order_item_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productSaved = productService.saveProductInfoOfOrderItem(product, order_item_id);
        return productSaved.orElseThrow(() -> new ProductNotSavedException("Product Data is not Saved"));
    }
    @PostMapping("/save/{shipping_cart_item_id}")
    public Product saveProductInfoOfShoppingCartItem(@RequestBody Product product, @PathVariable long shipping_cart_item_id) {
        if (!validateShoppingCartItem.checkValidShoppingCartItem(shipping_cart_item_id))
            throw new ShoppingCartItemNotFoundException("There is no shipping cart item with ID " + shipping_cart_item_id);


        logger.info("/api/v1/product/save/{shipping_cart_item_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productSaved = productService.saveProductInfoOfShoppingCartItem(product, shipping_cart_item_id);
        return productSaved.orElseThrow(() -> new ProductNotSavedException("Product Data is not Saved"));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct() {
        logger.info("/api/v1/product/all api resource is being fetched");
        Optional<List<Product>> productList = productService.getAllProducts();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(productList.orElseThrow(() -> new ProductNotFoundException("Product Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

  @GetMapping("/products/{order_item_id}")
    public List<Product> getProductsByOrderItemId(@PathVariable("order_item_id") long order_item_id) {
        logger.info("Checking validity of order_item item with order_item item id " + order_item_id);

        if (!validateOrderItem.checkValidOrderItem(order_item_id))
            throw new OrderItemNotFoundException("There is no Order item with ID " + order_item_id);

        logger.info("/api/v1/product/products/{order_item_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productsByOrderItemId = productService.getProductsByOrderItemId(order_item_id);
        return productsByOrderItemId.orElseThrow(() -> new ProductNotFoundException("Product Data with Order Item id " + order_item_id + " is not found"));
    }
    @GetMapping("/products/{shipping_cart_item_id}")
    public List<Product> getProductsByShoppingCartItemId(@PathVariable("shipping_cart_item_id") long shipping_cart_item_id) {
        logger.info("Checking validity of shopping Cart Item with shopping cart item id " + shipping_cart_item_id);

        if (!validateShoppingCartItem.checkValidShoppingCartItem(shipping_cart_item_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + shipping_cart_item_id);

        logger.info("/api/v1/product/products/{shipping_cart_item_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productsByCustomerId = productService.getProductsByShoppingCartItemId(shipping_cart_item_id);
        return productsByCustomerId.orElseThrow(() -> new ProductNotFoundException("Product Data with shopping cart item id " + shipping_cart_item_id + " is not found"));
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        logger.info("/api/v1/product/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productById = productService.getProductById(id);
        return productById.orElseThrow(() -> new ProductNotFoundException("Product Data with id " + id + " is not found"));
    }


    @GetMapping("/product-name/{product_name}")
    public List<Product> getProductsByProductName(@PathVariable("product_name") String productName) {
        logger.info("/api/v1/product/product-name/{product_name} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productByProductDateTime = productService.getProductsByProductName(productName);
        return productByProductDateTime.orElseThrow(() -> new ProductNotFoundException("Product Data with the specified product name of  " + productName + " is not found"));


    }

    @GetMapping("/quantity-avail/{quantity_avail}")
    public List<Product> getProductsByQuantityAvail(@PathVariable("quantity_avail") long quantityAvail) {
        logger.info("/api/v1/product/quantity-avail/{quantity_avail} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productsByQuantityAvail = productService.getProductsByQuantityAvail(quantityAvail);
        return productsByQuantityAvail.orElseThrow(() -> new ProductNotFoundException("Product Data with the quantity available of  " + quantityAvail + " is not found"));


    }

    @GetMapping("/unit-price/{unit_price}")
    public List<Product> getProductsByUnitPrice(@PathVariable("unit_price") double unitPrice) {
        logger.info("/api/v1/product/unit-price/{unit_price} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productsByUnitPrice = productService.getProductsByUnitPrice(unitPrice);
        return productsByUnitPrice.orElseThrow(() -> new ProductNotFoundException("Product Data with the specified Unit Price of  " + unitPrice + " is not found"));


    }

    @GetMapping("/mfg-date/{mfg_date}")
    public List<Product> getProductsByMfgDate(@PathVariable("mfg_date") LocalDate MfgDate) {
        logger.info("/api/v1/product/mfg-date/{mfg_date} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productsByMfgDate = productService.getProductsByMfgDate(MfgDate);
        return productsByMfgDate.orElseThrow(() -> new ProductNotFoundException("Product Data with the specified manufactured date of  " + MfgDate + " is not found"));


    }

    @GetMapping("/description/{description}")
    public List<Product> getProductsByDescription(@PathVariable("description") String description) {
        logger.info("/api/v1/product/description/{description} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Product>> productByProductDateTime = productService.getProductsByDescription(description);
        return productByProductDateTime.orElseThrow(() -> new ProductNotFoundException("Product Data with the description of  " + description + " is not found"));
  }

    @DeleteMapping("/delete")
    public Product deleteProduct(@RequestBody Product product) {
        logger.info("/api/v1/product/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> productDeleted = productService.deleteProduct(product);
        return productDeleted.orElseThrow(() -> new ProductNotDeletedException("Product Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProductById(@PathVariable("id") long id) {
        logger.info("/api/v1/product/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Product> product = productService.getProductById(id);
        if (!product.isPresent() || !product.isEmpty())
            throw new ProductNotFoundException("There is no Product with id " + id + " . Hence, it is not deleted");


        Optional<Product> productDeleted = productService.deleteProductById(id);
        return productDeleted.orElseThrow(() -> new ProductNotDeletedException("Product Data is not Deleted"));
    }


}

