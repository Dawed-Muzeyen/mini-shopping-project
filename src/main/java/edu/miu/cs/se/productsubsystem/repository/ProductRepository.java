package edu.miu.cs.se.productsubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.productsubsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);
    List<Product> findByQuantityAvail(long quantityAvail);
    List<Product> findByUnitPrice(double unitPrice);
    List<Product> findByMfgDate(LocalDate mfgDate);
    List<Product> findByDescription(String description);

}
