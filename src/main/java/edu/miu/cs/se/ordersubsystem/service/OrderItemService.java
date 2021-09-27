package edu.miu.cs.se.ordersubsystem.service;

import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.ordersubsystem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;


@Service
@Transactional
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderRepository) {
        this.orderItemRepository = orderRepository;
    }

    public Optional<List<OrderItem>> getOrderItemsByOrderId(long orderId) {
        return orderItemRepository.selectOrderItemsByOrderId(orderId);
    }
    public Optional<OrderItem> saveOrderItem(OrderItem orderItem) {
        return Optional.of(orderItemRepository.save(orderItem));
    }

    public  Optional<List<OrderItem>> getAllOrderItems() {
        return Optional.of(orderItemRepository.findAll());

    }



    public Optional<OrderItem> getOrderItemById(long id) {


        return orderItemRepository.findById(id);
    }

    public Optional<OrderItem> deleteOrderItem(OrderItem orderItem) {

        orderItemRepository.delete(orderItem);
        return Optional.of(orderItem);
    }

    public Optional<OrderItem> deleteOrderItemById(long id) {

        Optional<OrderItem> orderItemDeleted =  orderItemRepository.findById(id);
        orderItemRepository.deleteById(id);
        return orderItemDeleted;

    }

    public Optional<OrderItem> updateOrderItemById(long id, OrderItem orderItem) {
        OrderItem orderItemTemp = orderItemRepository.getById(id);

        if (!Optional.of(orderItem.getQuantity()).isEmpty())
            orderItemTemp.setQuantity(orderItem.getQuantity());
        orderItemTemp.setTotalPrice(orderItem.getTotalPrice());
        orderItemTemp.setProduct(orderItem.getProduct());

        return Optional.of(orderItemRepository.save(orderItemTemp));

    }

    public Optional<List<OrderItem>> getOrderItemsByQuantity(long quantity) {
        return orderItemRepository.findByQuantity(quantity);

    }
    public Optional<List<OrderItem>> getOrderItemsByTotalPrice(double totalPrice) {
        return orderItemRepository.findByTotalPrice(totalPrice);

    }

    public Optional<OrderItem> saveOrderItemInfoOfOrder(OrderItem orderItem, long order_id) {
        Set<Long> allIds = orderItemRepository.allOrderItemIds();
        long id = new Random().nextLong();
        while(allIds.contains(id)){
            id = new Random().nextLong();
        }
        orderItemRepository.insertOrderItemInfoOfOrder(id, orderItem.getQuantity(), orderItem.getTotalPrice(), order_id);
        return Optional.of(orderItem);
    }

}
