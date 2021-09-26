package edu.miu.cs.se.productsubsystem.repository;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.productsubsystem.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    List<Catalog> findByName(String name);

}
