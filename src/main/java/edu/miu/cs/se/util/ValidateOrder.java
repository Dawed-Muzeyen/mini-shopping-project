package edu.miu.cs.se.util;

import edu.miu.cs.se.ordersubsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateOrder {

    private OrderRepository orderRepository;


    @Autowired
    public ValidateOrder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }




    public  boolean checkValidOrder(long id) {
        Set<Long> allIds = orderRepository.allOrderIds();
        return allIds.contains(id);
    }
}
