package edu.miu.cs.se.productsubsystem.controller;

import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/Product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/update/{id}")
    public Product updateCatalogById(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product Product) {
        return productService.saveProduct(Product);
    }

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }


    @GetMapping("/product-name/{product_name}")
    public List<Product> getProductsByProductName(@PathVariable("product_name") String productName) {
        return productService.getProductsByProductName(productName);

    }

    @GetMapping("/quantity-avail/{quantity_avail}")
    public List<Product> getProductsByQuantityAvail(@PathVariable("quantity_avail") long quantityAvail) {
        return productService.getProductsByQuantityAvail(quantityAvail);

    }

    @GetMapping("/unit-price/{unit_price}")
    public List<Product> getProductsByUnitPrice(@PathVariable("unit_price") double unitPrice) {
        return productService.getProductsByUnitPrice(unitPrice);

    }

    @GetMapping("/mfg-date/{mfg_date}")
    public List<Product> getProductsByMfgDate(@PathVariable("mfg_date") LocalDate MfgDate) {
        return productService.getProductsByMfgDate(MfgDate);

    }

    @GetMapping("/description/{description}")
    public List<Product> getProductsByDescription(@PathVariable("description") String description) {
        return productService.getProductsByDescription(description);

    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody Product Product) {
        productService.deleteProduct(Product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable("id") long id) {
        productService.deleteProductById(id);
    }


}

