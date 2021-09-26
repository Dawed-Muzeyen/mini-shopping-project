package edu.miu.cs.se.productsubsystem.service;

import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogService {
    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }

    public Catalog saveCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    public Catalog getCatalogById(long id) {
        return catalogRepository.findById(id).get();
    }

    public void deleteCatalog(Catalog catalog) {
         catalogRepository.delete(catalog);
    }

    public void deleteCatalogById(long id) {
        catalogRepository.deleteById(id);
    }

    public Catalog updateCatalogById(long id, Catalog catalog) {
        Catalog catalogTemp = catalogRepository.getById(id);

        if(!Optional.of(catalog.getName()).isEmpty())
        catalogTemp.setName(catalog.getName());
        return catalogRepository.save(catalogTemp);
    }

    public List<Catalog> getCatalogsByName(String name){
        return catalogRepository.findByName(name);
    }
}
