package edu.miu.cs.se.util;

import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidatePayment {

    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ValidatePayment(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public  boolean checkValidPayment(long id) {
        Set<Long> allIds = shoppingCartRepository.allShoppingCartIds();
        return allIds.contains(id);
    }
}
