package edu.miu.cs.se.util;

import edu.miu.cs.se.customersubsystem.repository.CustomerRepository;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateShoppingCart {

    private ShoppingCartRepository shoppingCartRepository;


    @Autowired
    public ValidateShoppingCart(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }




    public  boolean checkValidShoppingCart(long id) {
        Set<Long> allIds = shoppingCartRepository.allShoppingCartIds();
        return allIds.contains(id);
    }
}
