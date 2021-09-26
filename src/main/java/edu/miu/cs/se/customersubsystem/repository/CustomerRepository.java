package edu.miu.cs.se.customersubsystem.repository;


import edu.miu.cs.se.customersubsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where first_name = :first_name AND last_name = :last_name",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByFirstNameAndLastName(@Param("first_name") String first_name, @Param("last_name") String last_name);

    Optional<List<Customer>> findByFirstName(String first_name);

    Optional<List<Customer>> findByLastName(String last_name);

    Optional<List<Customer>> findByEmail(String email);

    Optional<List<Customer>> findByPhoneNumber(String phoneNumber);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where ship_street1 = :ship_street1",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByShipStreet1(@Param("ship_street1") String ship_street1);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where ship_street2 = :ship_street2",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByShipStreet2(@Param("ship_street2") String ship_street2);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where ship_city = :ship_city",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByShipCity(@Param("ship_city") String ship_city);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where ship_state = :ship_state",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByShipState(@Param("ship_state") String ship_state);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where ship_zip = :ship_zip",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByShipZip(@Param("ship_zip") String ship_zip);
//hhhhhhh
@Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
        "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
        "from customer where bill_street1 = :bill_street1",
        nativeQuery = true
)
Optional<List<Customer>> selectCustomerByBillStreet1(@Param("bill_street1") String bill_street1);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where bill_street2 = :bill_street2",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByBillStreet2(@Param("bill_street2") String bill_street2);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where bill_city = :bill_city",
            nativeQuery = true
    )
    Optional<List<Customer>>selectCustomerByBillCity(@Param("bill_city") String bill_city);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where bill_state = :bill_state",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByBillState(@Param("bill_state") String bill_state);

    @Query(value = "select id, first_name, last_name, email, phone_number, ship_street1, ship_street2, " +
            "ship_city, ship_state, ship_zip, bill_street1, bill_street2, bill_city, bill_state, bill_zip " +
            "from customer where bill_zip = :bill_zip",
            nativeQuery = true
    )
    Optional<List<Customer>> selectCustomerByBillZip(@Param("bill_zip") String bill_zip);

    @Query(value = "select first_name " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerFirstName();

    @Query(value = "select last_name " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerLastName();

    @Query(value = "select email " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerEmail();

    @Query(value = "select phone_number " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerPhoneNumber();

    @Query(value = "select ship_street1 " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerShipStreet1();

    @Query(value = "select ship_street2 " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerShipStreet2();

    @Query(value = "select ship_city " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerShipCity();

    @Query(value = "select ship_state " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerShipState();

    @Query(value = "select ship_zipcode " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerShipZip();

    @Query(value = "select bill_street1 " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerBillStreet1();

    @Query(value = "select bill_street2 " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerBillStreet2();

    @Query(value = "select bill_city " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerBillCity();

    @Query(value = "select bill_state " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerBillState();

    @Query(value = "select bill_zip " +
            "from customer ",
            nativeQuery = true
    )
    List<String> selectAllCustomerBillZip();

    @Query(value = "select id from Customer" ,
            nativeQuery = true
    )
    Set<Long> allCustomerIds();
}

