package edu.miu.cs.se.util;

import edu.miu.cs.se.productsubsystem.repository.ProductRepository;
import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidateProduct {

    private ProductRepository productRepository;

    @Autowired
    public ValidateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  boolean checkValidProduct(long id) {
        Set<Long> allIds = productRepository.allProductIds();
        return allIds.contains(id);
    }
}
