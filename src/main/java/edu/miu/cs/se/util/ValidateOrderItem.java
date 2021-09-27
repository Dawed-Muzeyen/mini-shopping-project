package edu.miu.cs.se.util;

import edu.miu.cs.se.ordersubsystem.repository.OrderItemRepository;
import edu.miu.cs.se.ordersubsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class ValidateOrderItem {

    private OrderItemRepository orderItemRepository;


    @Autowired
    public ValidateOrderItem(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }




    public  boolean checkValidOrderItem(long id) {
        Set<Long> allIds = orderItemRepository.allOrderItemIds();
        return allIds.contains(id);
    }
}
