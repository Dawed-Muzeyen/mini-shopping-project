package edu.miu.cs.se.productsubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.productsubsystem.model.Catalog;
import edu.miu.cs.se.productsubsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into product(id, product_name, quantity_avail, unit_price, mfg_date, description, order_item_id)"+
                    "values (:id, :product_name, :quantity_avail, :unit_price, :mfg_date, :description, :order_item_id)",
            nativeQuery = true)
    void insertProductInfoOfOrderItem(@Param("id") long id, @Param("product_name") String product_name,
                                      @Param("quantity_avail") long quantity_avail,  @Param("unit_price") double unit_price,
                                      @Param("mfg_date") LocalDate mfg_date, @Param("description") String description, @Param("order_item_id") long order_item_id);
    @Modifying
    @Transactional
    @Query(value = "insert into product(id, product_name, quantity_avail, unit_price, mfg_date, description, shopping_cart_item_id)"+
                   "values (:id, :product_name, :quantity_avail, :unit_price, :mfg_date, :description, :shopping_cart_item_id)",
            nativeQuery = true)
    void insertProductInfoOfShoppingCartItem(@Param("id") long id, @Param("product_name") String product_name,
                                      @Param("quantity_avail") long quantity_avail,  @Param("unit_price") double unit_price,
                                      @Param("mfg_date") LocalDate mfg_date, @Param("description") String description, @Param("shopping_cart_item_id") long shopping_cart_item_id);


    @Query(value = "select * " +
            "from product p INNER JOIN order_item o on p.order_item_id = o.id and p.order_item_id = :order_item_id ",
            nativeQuery = true
    )
    Optional<List<Product>> selectProductsByOrderItemId(@Param("order_item_id") long order_item_id);

    @Query(value = "select * " +
            "from product p INNER JOIN shopping_cart_item o on p.shopping_cart_item_id = o.id and p.shopping_cart_item_id = :shopping_cart_item_id ",
            nativeQuery = true
    )
    Optional<List<Product>> selectProductsByShoppingCartItemId(@Param("shopping_cart_item_id") long shopping_cart_item_id);

    Optional<List<Product>> findByProductName(String productName);
    Optional<List<Product>> findByQuantityAvail(long quantityAvail);
    Optional<List<Product>> findByUnitPrice(double unitPrice);
    Optional<List<Product>> findByMfgDate(LocalDate mfgDate);
    Optional<List<Product>> findByDescription(String description);

    @Query(value = "select id from product" ,
            nativeQuery = true
    )
     Set<Long> allProductIds();

}
