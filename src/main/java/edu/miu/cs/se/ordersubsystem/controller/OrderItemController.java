package edu.miu.cs.se.ordersubsystem.controller;


import edu.miu.cs.se.exceptions.order.OrderNotFoundException;
import edu.miu.cs.se.exceptions.orderitem.OrderItemNotDeletedException;
import edu.miu.cs.se.exceptions.orderitem.OrderItemNotFoundException;
import edu.miu.cs.se.exceptions.orderitem.OrderItemNotModifiedException;
import edu.miu.cs.se.exceptions.orderitem.OrderItemNotSavedException;
import edu.miu.cs.se.ordersubsystem.model.OrderItem;
import edu.miu.cs.se.ordersubsystem.service.OrderItemService;
import edu.miu.cs.se.util.ValidateOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order-item")
public class OrderItemController {
    private OrderItemService orderItemService;
    private ValidateOrder validateOrder;

    private Logger logger = LoggerFactory.getLogger(OrderItem.class);

    @Autowired
    public OrderItemController(OrderItemService orderItemService, ValidateOrder validateOrder) {
        this.orderItemService = orderItemService;
        this.validateOrder = validateOrder;
    }


    @PutMapping("/update/{id}")
    public OrderItem updateOrderItemById(@PathVariable("id") long id, @RequestBody OrderItem orderItem) {
        logger.info("/api/v1/order-item/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItemUpdated = orderItemService.updateOrderItemById(id, orderItem);
        return orderItemUpdated.orElseThrow(() -> new OrderItemNotModifiedException("OrderItem Data is not Updated"));

    }


    @PostMapping("/save")
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem) {
        logger.info("/api/v1/order-item/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItemSaved = orderItemService.saveOrderItem(orderItem);
        return orderItemSaved.orElseThrow(() -> new OrderItemNotSavedException("OrderItem Data is not Saved"));


    }

    @PostMapping("/save/{order_id}")
    public OrderItem saveOrderItemInfoOfOrder(@RequestBody OrderItem orderItem, @PathVariable long order_id) {
        if (!validateOrder.checkValidOrder(order_id))
            throw new OrderNotFoundException("There is no Order with ID " + order_id);


        logger.info("/api/v1/order-item/save/{order_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItemSaved = orderItemService.saveOrderItemInfoOfOrder(orderItem, order_id);
        return orderItemSaved.orElseThrow(() -> new OrderItemNotSavedException("OrderItem Data is not Saved"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        logger.info("/api/v1/order-item/all api resource is being fetched");
        Optional<List<OrderItem>> orderItemList = orderItemService.getAllOrderItems();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(orderItemList.orElseThrow(() -> new OrderItemNotFoundException("OrderItem Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/order-items/{order_id}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable("order_id") long order_id) {
        logger.info("Checking validity of Customer with Order id " + order_id);

        if (!validateOrder.checkValidOrder(order_id))
            throw new OrderNotFoundException("There is no Order data with ID " + order_id);

        logger.info("/api/v1/order-item/orderItems/{order_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<OrderItem>> orderItemsByOrderId = orderItemService.getOrderItemsByOrderId(order_id);
        return orderItemsByOrderId.orElseThrow(() -> new OrderItemNotFoundException("OrderItem Data with customer id " + order_id + " is not found"));

    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable("id") long id) {
        logger.info("/api/v1/order-item/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItemById = orderItemService.getOrderItemById(id);
        return orderItemById.orElseThrow(() -> new OrderItemNotFoundException("OrderItem Data with id " + id + " is not found"));
    }


    @GetMapping("/quantity/{quantity}")
    public List<OrderItem> getOrderItemsByQuantity(@PathVariable("quantity") long quantity) {
        logger.info("/api/v1/order-item/quantity/{quantity} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<OrderItem>> orderItemByQuantity = orderItemService.getOrderItemsByQuantity(quantity);
        return orderItemByQuantity.orElseThrow(() -> new OrderItemNotFoundException("OrderItem Data with quantity " + quantity + " is not found"));


    }

    @GetMapping("/total-price/{total_price}")
    public List<OrderItem> getOrderItemsByTotalPrice(@PathVariable("total_price") double totalPrice) {
        logger.info("/api/v1/order-item/total-price/{total_price} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<OrderItem>> orderItemsByTotalPrice = orderItemService.getOrderItemsByTotalPrice(totalPrice);
        return orderItemsByTotalPrice.orElseThrow(() -> new OrderItemNotFoundException("OrderItem Data with the specified total price of  " + totalPrice + " is not found"));

    }

    @DeleteMapping("/delete")
    public OrderItem deleteOrderItem(@RequestBody OrderItem orderItem) {
        logger.info("/api/v1/order-item/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItemDeleted = orderItemService.deleteOrderItem(orderItem);
        return orderItemDeleted.orElseThrow(() -> new OrderItemNotDeletedException("OrderItem Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public OrderItem deleteOrderItemById(@PathVariable("id") long id) {
        logger.info("/api/v1/order-item/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        if (!orderItem.isPresent() || !orderItem.isEmpty())
            throw new OrderItemNotFoundException("There is no OrderItem with id " + id + " . Hence, it is not deleted");


        Optional<OrderItem> orderItemDeleted = orderItemService.deleteOrderItemById(id);
        return orderItemDeleted.orElseThrow(() -> new OrderItemNotDeletedException("OrderItem Data is not Deleted"));

    }

}