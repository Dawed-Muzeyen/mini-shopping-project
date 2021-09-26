package edu.miu.cs.se.ordersubsystem.service;

import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.ordersubsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }




    public Optional<Order> saveOrder(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    public Optional<List<Order>> getAllOrders() {
        return Optional.of(orderRepository.findAll());

    }

    public Optional<List<Order>> getOrdersByCustomerId(long customer_id) {
       return orderRepository.selectOrdersByCustomerId(customer_id);
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    public  Optional<Order> deleteOrder(Order order) {

        orderRepository.delete(order);
        return Optional.of(order);

    }

    public Optional<Order> deleteOrderById(long id) {

        Optional<Order> orderDeleted =  orderRepository.findById(id);
        orderRepository.deleteById(id);
        return orderDeleted;

    }

    public Optional<Order> updateOrderById(long id, Order order) {
        Order orderTemp = orderRepository.getById(id);

        if (!Optional.of(order.getOrderDateTime()).isEmpty())
            orderTemp.setOrderDateTime(order.getOrderDateTime());

        if (!Optional.of(order.getTotalPrice()).isEmpty())
        orderTemp.setTotalPrice(order.getTotalPrice());

        if (!Optional.of(order.getOrderItems()).isEmpty())
        orderTemp.setOrderItems(order.getOrderItems());

        return Optional.of(orderRepository.save(order));
    }

    public Optional<List<Order>> getOrdersByOrderDateTime(LocalDateTime orderDateTime) {
        return orderRepository.findByOrderDateTime(orderDateTime);

    }
    public Optional<List<Order>> getOrdersByTotalPrice(double totalPrice) {
        return orderRepository.findByTotalPrice(totalPrice);

    }

    public Optional<Order> saveOrderInfoOfCustomer(Order order, long customer_id) {



        Set<Long> allIds = orderRepository.allOrderIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
         orderRepository.insertOrderInfoOfCustomer(id, order.getOrderDateTime(), order.getTotalPrice(), customer_id);
        return Optional.of(order);
    }
}
