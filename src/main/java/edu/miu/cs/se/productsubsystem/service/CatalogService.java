package edu.miu.cs.se.productsubsystem.service;

import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class CatalogService {
    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public  Optional<List<Catalog>> getAllCatalog() {
        return Optional.of(catalogRepository.findAll());
    }

    public  Optional<Catalog> saveCatalog(Catalog catalog) {
        return Optional.of(catalogRepository.save(catalog));
    }

    public  Optional<Catalog> getCatalogById(long id) {
        return catalogRepository.findById(id);
    }

    public  Optional<Catalog> deleteCatalog(Catalog catalog) {
         catalogRepository.delete(catalog);
        return Optional.of(catalog);
    }

    public  Optional<Catalog> deleteCatalogById(long id) {
        Optional<Catalog> catalogDeleted =  catalogRepository.findById(id);
        catalogRepository.deleteById(id);
        return catalogDeleted;

    }

    public  Optional<Catalog> updateCatalogById(long id, Catalog catalog) {
        Catalog catalogTemp = catalogRepository.getById(id);

        if(!Optional.of(catalog.getName()).isEmpty())
        catalogTemp.setName(catalog.getName());
        return Optional.of(catalogRepository.save(catalogTemp));
    }

    public  Optional<List<Catalog>> getCatalogsByName(String name){
        return catalogRepository.findByName(name);
    }

    public Optional<Catalog> saveCatalogInfoOfProduct(Catalog catalog, long product_id) {
        Set<Long> allIds = catalogRepository.allCatalogIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        catalogRepository.insertCatalogInfoOfProduct(id, catalog.getName(), product_id);
        return Optional.of(catalog);
    }

    public Optional<List<Catalog>> getCatalogsByProductId(long product_id){
        return catalogRepository.selectCatalogsByProductId(product_id);
    }

}
