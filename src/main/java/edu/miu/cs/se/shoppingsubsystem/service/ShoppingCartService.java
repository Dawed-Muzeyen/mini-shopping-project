package edu.miu.cs.se.shoppingsubsystem.service;

import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
import edu.miu.cs.se.shoppingsubsystem.repository.ShoppingCartRepository;
import edu.miu.cs.se.util.ValidateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ValidateCustomer validateCustomer;
    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ValidateCustomer validateCustomer) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.validateCustomer = validateCustomer;
    }

    public Optional<ShoppingCart> saveShoppingCart(ShoppingCart shoppingCart) {
        return Optional.of(shoppingCartRepository.save(shoppingCart));
    }

    public Optional<List<ShoppingCart>> getAllShoppingCarts() {
        return Optional.of(shoppingCartRepository.findAll());

    }

    public Optional<List<ShoppingCart>> getShoppingCartsByCustomerId(long customer_id) {
       return shoppingCartRepository.selectShoppingCartsByCustomerId(customer_id);
    }


    public Optional<ShoppingCart> getShoppingCartById(long id) {
        return shoppingCartRepository.findById(id);
    }

    public Optional<ShoppingCart> deleteShoppingCart(ShoppingCart shoppingCart) {



        shoppingCartRepository.delete(shoppingCart);
        return Optional.of(shoppingCart);
    }

    public Optional<ShoppingCart>  deleteShoppingCartById(long id) {

        Optional<ShoppingCart> shoppingCartDeleted =  shoppingCartRepository.findById(id);
        shoppingCartRepository.deleteById(id);
        return shoppingCartDeleted;
    }

    public Optional<ShoppingCart> updateShoppingCartById(long id, ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartTemp = shoppingCartRepository.getById(id);

        if (!Optional.of(shoppingCart.getOrderDateTime()).isEmpty())
            shoppingCartTemp.setOrderDateTime(shoppingCart.getOrderDateTime());
        if (!Optional.of(shoppingCart.getTotalPrice()).isEmpty())
        shoppingCartTemp.setTotalPrice(shoppingCart.getTotalPrice());
        if (!Optional.of(shoppingCart.getCartItems()).isEmpty())
        shoppingCartTemp.setCartItems(shoppingCart.getCartItems());


        return Optional.of(shoppingCartRepository.save(shoppingCartTemp));

    }

    public Optional<List<ShoppingCart>> getShoppingCartsByOrderDateTime(LocalDateTime orderDateTime) {
        return shoppingCartRepository.findByOrderDateTime(orderDateTime);

    }
    public Optional<List<ShoppingCart>> getShoppingCartsByTotalPrice(double totalPrice) {
        return shoppingCartRepository.findByTotalPrice(totalPrice);

    }

    public Optional<ShoppingCart> saveShoppingCartInfoOfCustomer(ShoppingCart shoppingCart, long customer_id) {


        Set<Long> allIds = shoppingCartRepository.allShoppingCartIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        shoppingCartRepository.insertShoppingCartInfoOfCustomer(id, shoppingCart.getOrderDateTime(), shoppingCart.getTotalPrice(), customer_id);
        return Optional.of(shoppingCart);
    }
}
