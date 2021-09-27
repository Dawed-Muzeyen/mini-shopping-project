package edu.miu.cs.se.shoppingsubsystem.service;

import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCartItem;
import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;


@Service
@Transactional
public class ShoppingCartItemService {

    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public Optional<List<ShoppingCartItem>> getShoppingCartItemsByShoppingCartId(long shoppingCartId) {
        return shoppingCartItemRepository.selectShoppingCartItemsByShoppingCartId(shoppingCartId);
    }
    public Optional<ShoppingCartItem> saveShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        return Optional.of(shoppingCartItemRepository.save(shoppingCartItem));
    }

    public  Optional<List<ShoppingCartItem>> getAllShoppingCartItems() {
        return Optional.of(shoppingCartItemRepository.findAll());

    }



    public Optional<ShoppingCartItem> getShoppingCartItemById(long id) {


        return shoppingCartItemRepository.findById(id);
    }

    public Optional<ShoppingCartItem> deleteShoppingCartItem(ShoppingCartItem shoppingCartItem) {

        shoppingCartItemRepository.delete(shoppingCartItem);
        return Optional.of(shoppingCartItem);
    }

    public Optional<ShoppingCartItem> deleteShoppingCartItemById(long id) {

        Optional<ShoppingCartItem> shoppingCartItemDeleted =  shoppingCartItemRepository.findById(id);
        shoppingCartItemRepository.deleteById(id);
        return shoppingCartItemDeleted;

    }

    public Optional<ShoppingCartItem> updateShoppingCartItemById(long id, ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem shoppingCartItemTemp = shoppingCartItemRepository.getById(id);

        if (!Optional.of(shoppingCartItem.getQuantity()).isEmpty())
            shoppingCartItemTemp.setQuantity(shoppingCartItem.getQuantity());
        shoppingCartItemTemp.setTotalPrice(shoppingCartItem.getTotalPrice());
        shoppingCartItemTemp.setProduct(shoppingCartItem.getProduct());

        return Optional.of(shoppingCartItemRepository.save(shoppingCartItemTemp));

    }

    public Optional<List<ShoppingCartItem>> getShoppingCartItemsByQuantity(long quantity) {
        return shoppingCartItemRepository.findByQuantity(quantity);

    }
    public Optional<List<ShoppingCartItem>> getShoppingCartItemsByTotalPrice(double totalPrice) {
        return shoppingCartItemRepository.findByTotalPrice(totalPrice);

    }

    public Optional<ShoppingCartItem> saveShoppingCartItemInfoOfShoppingCart(ShoppingCartItem shoppingCartItem, long order_id) {
        Set<Long> allIds = shoppingCartItemRepository.allShoppingCartItemIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        shoppingCartItemRepository.insertShoppingCartItemInfoOfShoppingCart(id, shoppingCartItem.getQuantity(), shoppingCartItem.getTotalPrice(), order_id);
        return Optional.of(shoppingCartItem);
    }
}
