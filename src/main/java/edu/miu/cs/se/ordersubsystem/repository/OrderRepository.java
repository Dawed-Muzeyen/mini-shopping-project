package edu.miu.cs.se.ordersubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.ordersubsystem.model.OrderItem;
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
    public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * " +
            "from orders o INNER JOIN customer c on o.customer_id = c.id and o.customer_id = :customer_id ",
            nativeQuery = true
    )
    Optional<List<Order>> selectOrdersByCustomerId(@Param("customer_id") long customer_id);

    @Modifying
    @Transactional
    @Query(value = "insert into order(id, order_date_time, total_price, customer_id) values (:id, :order_date_time, :total_price, :customer_id)",
            nativeQuery = true)
   void insertOrderInfoOfCustomer(@Param("id") long id, @Param("order_date_time") LocalDateTime order_date_time,
                                              @Param("total_price") double total_price, @Param("customer_id") long customer_id);

    Optional<List<Order>> findByOrderDateTime(LocalDateTime orderDateTime);
    Optional<List<Order>> findByTotalPrice(double totalPrice);

    @Query(value = "select id from orders" ,
            nativeQuery = true
    )
    Set<Long> allOrderIds();
}