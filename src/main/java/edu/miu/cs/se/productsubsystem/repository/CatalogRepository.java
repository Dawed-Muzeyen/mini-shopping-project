package edu.miu.cs.se.productsubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import edu.miu.cs.se.productsubsystem.model.Catalog;
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
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<List<Catalog>> findByName(String name);


    @Modifying
    @Transactional
    @Query(value = "insert into catalog(id, name, product_id) values (:id, :name, :product_id)",
            nativeQuery = true)
    void insertCatalogInfoOfProduct(@Param("id") long id, @Param("name") String name, @Param("product_id") long product_id);


    @Query(value = "select * " +
            "from catalog c INNER JOIN product p on c.product_id = p.id and c.product_id = :product_id ",
            nativeQuery = true
    )
    Optional<List<Catalog>> selectCatalogsByProductId(@Param("product_id") long product_id);


    @Query(value = "select id from catalog" ,
            nativeQuery = true
    )
    Set<Long> allCatalogIds();
}
