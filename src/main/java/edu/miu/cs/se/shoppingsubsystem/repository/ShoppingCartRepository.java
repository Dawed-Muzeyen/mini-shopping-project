package edu.miu.cs.se.shoppingsubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.shoppingsubsystem.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
    public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select * " +
            "from shopping_cart s INNER JOIN customer c on s.customer_id = c.id and s.customer_id = :customer_id ",
            nativeQuery = true
    )
    Optional<List<ShoppingCart>> selectShoppingCartsByCustomerId(@Param("customer_id") long customer_id);

    @Modifying
    @Transactional
    @Query(value = "insert into shopping_cart(id, order_date_time, total_price, customer_id) values (:id, :order_date_time, :total_price, :customer_id)",
            nativeQuery = true)
    void insertShoppingCartInfoOfCustomer(@Param("id") long id, @Param("order_date_time") LocalDateTime order_date_time,
                                          @Param("total_price") double total_price, @Param("customer_id") long customer_id);

    Optional<List<ShoppingCart>> findByOrderDateTime(LocalDateTime orderDateTime);
    Optional<List<ShoppingCart>> findByTotalPrice(double totalPrice);

    @Query(value = "select id from shopping_cart" ,
            nativeQuery = true
    )
    Set<Long> allShoppingCartIds();
    }