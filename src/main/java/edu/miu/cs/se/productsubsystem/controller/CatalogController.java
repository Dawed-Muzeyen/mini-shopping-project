package edu.miu.cs.se.productsubsystem.controller;

import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.service.CatalogService;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
    @PutMapping("/update/{id}")
    public Catalog updateCatalogById(@PathVariable("id") long id, @RequestBody Catalog catalog) {
        return catalogService.updateCatalogById(id, catalog);
    }
    @PostMapping("/save")
    public Catalog saveCatalog(@RequestBody Catalog catalog){
        return catalogService.saveCatalog(catalog);
    }

    @GetMapping("/all")
    public List<Catalog> getAllCatalog() {
        return catalogService.getAllCatalog();
    }

    @GetMapping("/{id}")
    public Catalog getCatalogById(@PathVariable("id")long id) {
        return catalogService.getCatalogById(id);
    }



    @GetMapping("/name/{name}")
    public List<Catalog> getCatalogsByName(@PathVariable("name") String name) {
        return catalogService.getCatalogsByName(name);

    }
    @DeleteMapping("/delete")
    public void deleteCatalog(@RequestBody Catalog catalog) {
        catalogService.deleteCatalog(catalog);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCatalogById(@PathVariable("id") long id) {
        catalogService.deleteCatalogById(id);
    }

}
