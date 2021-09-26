package edu.miu.cs.se.ordersubsystem.repository;

import edu.miu.cs.se.customersubsystem.model.Customer;
import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.productsubsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = "select * " +
            "from order_item oi INNER JOIN orders o on oi.order_items_id = o.id and oi.order_items_id = :order_items_id ",
            nativeQuery = true
    )
    Optional<List<OrderItem>> selectOrderItemsByOrderId(@Param("order_items_id") long order_items_id);

    @Modifying
    @Transactional
    @Query(value = "insert into order_item(id, quantity, total_price, order_id) values (:id, :quantity, :total_price, :order_id)",
            nativeQuery = true)
    void insertOrderItemInfoOfOrder(@Param("id") long id, @Param("quantity") long quantity,
                                                  @Param("total_price") double total_price,@Param("order_id") long order_id);

    Optional<List<OrderItem>> findByQuantity(long quantity);
    Optional<List<OrderItem>> findByTotalPrice(double totalPrice);
    @Query(value = "select id from shopping_cart" ,
            nativeQuery = true
    )
    Set<Long> allOrderIds();
}

