package edu.miu.cs.se.paymentsubsystem.repository;


import edu.miu.cs.se.paymentsubsystem.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Repository
@Transactional
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into credit_card(id, name_on_card, expiration_date, card_num, card_type, customer_id) values (:id, :name_on_card, :expiration_date, :card_num, :card_type, :customer_id)",
            nativeQuery = true)
   void insertCreditCardInfoOfCustomer(@Param("id") long id, @Param("name_on_card") String name_on_card, @Param("expiration_date") String expiration_date,
                                                  @Param("card_num") String card_num, @Param("card_type") String card_type, @Param("customer_id") long customer_id);


    @Query(value = "select * " +
            "from credit_card cr INNER JOIN customer c on cr.customer_id = c.id and cr.customer_id = :customer_id ",
            nativeQuery = true
    )
     List<CreditCard> selectCreditCardsByCustomerId(@Param("customer_id") long customer_id);

     List<CreditCard> findByNameOnCard(String nameOnCard);
     List<CreditCard> findByExpirationDate(String expirationDate);
     List<CreditCard> findByCardNum(String cardNum);
     List<CreditCard> findByCardType(String cardType);

    @Query(value = "select id from credit_card" ,
            nativeQuery = true
    )
    Set<Long> allCreditCardIds();
}
