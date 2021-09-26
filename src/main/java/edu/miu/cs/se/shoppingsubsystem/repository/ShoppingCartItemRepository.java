package edu.miu.cs.se.shoppingsubsystem.repository;

import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    @Query(value = "select * " +
            "from order_item oi INNER JOIN orders o on oi.order_id = o.id and oi.order_id = :order_id ",
            nativeQuery = true
    )
    Optional<List<ShoppingCartItem>> selectShoppingCartItemsByShoppingCartId(@Param("order_id") long order_id);

    @Modifying
    @Transactional
    @Query(value = "insert into shopping_cart(id, quantity, total_price, order_id) values (:id, :quantity, :total_price, :order_id)",
            nativeQuery = true)
    void insertShoppingCartItemInfoOfShoppingCart(@Param("id") long id, @Param("quantity") long quantity,
                                                  @Param("total_price") double total_price,@Param("order_id") long order_id);

    Optional<List<ShoppingCartItem>> findByQuantity(long quantity);
    Optional<List<ShoppingCartItem>> findByTotalPrice(double totalPrice);


    @Query(value = "select id from shopping_cart" ,
            nativeQuery = true
    )
    Set<Long> allShoppingCartIds();
}
