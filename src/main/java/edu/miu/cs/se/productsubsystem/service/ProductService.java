package edu.miu.cs.se.productsubsystem.service;

import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.productsubsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
    public Product updateProductById(long id, Product product) {
        Product productTemp = productRepository.getById(id);

        if(!Optional.of(product.getProductName()).isEmpty())
        productTemp.setProductName(product.getProductName());

        if(!Optional.of(product.getQuantityAvail()).isEmpty())
        productTemp.setQuantityAvail(product.getQuantityAvail());

        if(!Optional.of(product.getUnitPrice()).isEmpty())
        productTemp.setUnitPrice(product.getUnitPrice());

        if(!Optional.of(product.getMfgDate()).isEmpty())
        productTemp.setMfgDate(product.getMfgDate());

        if(!Optional.of(product.getDescription()).isEmpty())
        productTemp.setDescription(product.getDescription());

        if(!Optional.of(product.getCartItems()).isEmpty())
        productTemp.setCartItems(product.getCartItems());

        return productRepository.save(productTemp);
    }


    public List<Product> getProductsByProductName(String productName){
        return productRepository.findByProductName(productName);
    }
    public List<Product> getProductsByQuantityAvail(long quantityAvail) {
        return productRepository.findByQuantityAvail(quantityAvail);
    }
    public List<Product> getProductsByUnitPrice(double unitPrice) {
        return productRepository.findByUnitPrice(unitPrice);
    }
    public List<Product> getProductsByMfgDate(LocalDate mfgDate) {
        return productRepository.findByMfgDate(mfgDate);
    }
    public List<Product> getProductsByDescription(String description) {
            return productRepository.findByDescription(description);
    }
}
