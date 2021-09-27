package edu.miu.cs.se.util;

import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartItemRepository;
import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateShoppingCartItem {

    private ShoppingCartItemRepository shoppingCartItemRepository;


    @Autowired
    public ValidateShoppingCartItem(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }




    public  boolean checkValidShoppingCartItem(long id) {
        Set<Long> allIds = shoppingCartItemRepository.allShoppingCartItemIds();
        return allIds.contains(id);
    }
}
