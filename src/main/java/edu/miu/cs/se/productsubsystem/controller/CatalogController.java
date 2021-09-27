package edu.miu.cs.se.productsubsystem.controller;

import edu.miu.cs.se.exceptions.catalog.CatalogNotDeletedException;
import edu.miu.cs.se.exceptions.catalog.CatalogNotFoundException;
import edu.miu.cs.se.exceptions.catalog.CatalogNotSavedException;
import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotModifiedException;
import edu.miu.cs.se.exceptions.product.ProductNotFoundException;
import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.service.CatalogService;
import edu.miu.cs.se.util.ValidateProduct;
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
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private CatalogService catalogService;
    private Logger logger = LoggerFactory.getLogger(CatalogController.class);
    private ValidateProduct validateProduct;

    @Autowired
    public CatalogController(CatalogService catalogService, ValidateProduct validateProduct) {
        this.catalogService = catalogService;
        this.validateProduct = validateProduct;
    }
    @PutMapping("/update/{id}")
    public Catalog updateCatalogById(@PathVariable("id") long id, @RequestBody Catalog catalog) {
        logger.info("/api/v1/catalog/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalogUpdated = catalogService.updateCatalogById(id, catalog);
        return catalogUpdated.orElseThrow(() -> new OrderNotModifiedException("Catalog Data is not Updated"));
    }
    @PostMapping("/save")
    public Catalog saveCatalog(@RequestBody Catalog catalog){
        logger.info("/api/v1/catalog/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalogSaved = catalogService.saveCatalog(catalog);
        return catalogSaved.orElseThrow(() -> new CatalogNotSavedException("Catalog Data is not Saved"));

    }
    @PostMapping("/save/{product_id}")
    public Catalog saveCatalogInfoOfProduct(@RequestBody Catalog catalog, @PathVariable long product_id) {
        if (!validateProduct.checkValidProduct(product_id))
            throw new ProductNotFoundException("There is no Product with ID " + product_id);


        logger.info("/api/v1/catalog/save/{product_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalogSaved = catalogService.saveCatalogInfoOfProduct(catalog, product_id);
        return catalogSaved.orElseThrow(() -> new CatalogNotSavedException("Catalog Data is not Saved"));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Catalog>> getAllCatalog() {
        logger.info("/api/v1/catalog/all api resource is being fetched");
        Optional<List<Catalog>> catalogList = catalogService.getAllCatalog();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(catalogList.orElseThrow(() -> new CatalogNotFoundException("Catalog Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public Catalog getCatalogById(@PathVariable("id")long id) {
        logger.info("/api/v1/catalog/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalogById = catalogService.getCatalogById(id);
        return catalogById.orElseThrow(() -> new CatalogNotFoundException("Catalog Data with id " + id + " is not found"));
    }

    @GetMapping("/product/{product_id}")
    public List<Catalog> getCatalogsByProductId(@PathVariable long product_id){
        logger.info("Checking validity of Product with customer id " + product_id);

        if (!validateProduct.checkValidProduct(product_id))
            throw new CustomerNotFoundException("There is no Product with ID " + product_id);

        logger.info("/api/v1/catalog/catalogs/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Catalog>> catalogsByCustomerId = catalogService.getCatalogsByProductId(product_id);
        return catalogsByCustomerId.orElseThrow(() -> new CatalogNotFoundException("Catalog Data with Product id " + product_id + " is not found"));

    }

    @GetMapping("/name/{name}")
    public List<Catalog> getCatalogsByName(@PathVariable("name") String name) {
        logger.info("/api/v1/catalog/name/{name} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Catalog>> catalogsByNameOnCard = catalogService.getCatalogsByName(name);
        return catalogsByNameOnCard.orElseThrow(() -> new CatalogNotFoundException("Catalog Data with the name  " + name + " is not found"));
   }
    @DeleteMapping("/delete")
    public Catalog deleteCatalog(@RequestBody Catalog catalog) {
        logger.info("/api/v1/catalog/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalogDeleted = catalogService.deleteCatalog(catalog);
        return catalogDeleted.orElseThrow(() -> new CatalogNotDeletedException("Catalog Data is not Deleted"));
    }

    @DeleteMapping("/delete/{id}")
    public Catalog deleteCatalogById(@PathVariable("id") long id) {
        logger.info("/api/v1/catalog/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Catalog> catalog = catalogService.getCatalogById(id);
        if (!catalog.isPresent() || !catalog.isEmpty())
            throw new CatalogNotFoundException("There is no Catalog with id " + id + " . Hence, it is not deleted");


        Optional<Catalog> catalogDeleted = catalogService.deleteCatalogById(id);
        return catalogDeleted.orElseThrow(() -> new CatalogNotDeletedException("Catalog Data is not Deleted"));
    }

}
