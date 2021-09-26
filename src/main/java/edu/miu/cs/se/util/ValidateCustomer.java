package edu.miu.cs.se.util;

import edu.miu.cs.se.customersubsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateCustomer {

    private CustomerRepository customerRepository;


    @Autowired
    public ValidateCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }




    public  boolean checkValidCustomer(long id) {
        Set<Long> allIds = customerRepository.allCustomerIds();
        return allIds.contains(id);
    }
}
