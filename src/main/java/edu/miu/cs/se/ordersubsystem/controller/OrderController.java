package edu.miu.cs.se.ordersubsystem.controller;



import edu.miu.cs.se.exceptions.customer.CustomerNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotDeletedException;
import edu.miu.cs.se.exceptions.order.OrderNotFoundException;
import edu.miu.cs.se.exceptions.order.OrderNotModifiedException;
import edu.miu.cs.se.exceptions.order.OrderNotSavedException;
import edu.miu.cs.se.ordersubsystem.model.Order;
import edu.miu.cs.se.ordersubsystem.service.OrderService;
import edu.miu.cs.se.util.ValidateCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;
    private ValidateCustomer validateCustomer;
    private Logger logger = LoggerFactory.getLogger(Order.class);
    @Autowired
    public OrderController(OrderService orderService, ValidateCustomer validateCustomer) {
        this.orderService = orderService;
        this.validateCustomer = validateCustomer;
    }



    @PutMapping("/update/{id}")
    public Order updateOrderById(@PathVariable("id") long id, @RequestBody Order order) {
        logger.info("/api/v1/order/update/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> orderUpdated = orderService.updateOrderById(id, order);
        return orderUpdated.orElseThrow(() -> new OrderNotModifiedException("Order Data is not Updated"));
    }


    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order) {
        logger.info("/api/v1/order/save is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> orderSaved = orderService.saveOrder(order);
        return orderSaved.orElseThrow(() -> new OrderNotSavedException("Order Data is not Saved"));


    }

    @PostMapping("/save/{customer_id}")
    public Order saveOrderInfoOfCustomer(@RequestBody Order order, @PathVariable long customer_id) {
        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);


        logger.info("/api/v1/order/save/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> orderSaved = orderService.saveOrderInfoOfCustomer(order, customer_id);
        return orderSaved.orElseThrow(() -> new OrderNotSavedException("Order Data is not Saved"));
    }



    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("/api/v1/order/all api resource is being fetched");
        Optional<List<Order>> orderList = orderService.getAllOrders();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(orderList.orElseThrow(() -> new OrderNotFoundException("Order Data is Empty. No information at all")), responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/orders/{customer_id}")
    public List<Order> getOrdersByCustomerId(@PathVariable("customer_id") long customer_id) {
        logger.info("Checking validity of Customer with customer id " + customer_id);

        if (!validateCustomer.checkValidCustomer(customer_id))
            throw new CustomerNotFoundException("There is no Customer with ID " + customer_id);

        logger.info("/api/v1/order/orders/{customer_id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Order>> ordersByCustomerId = orderService.getOrdersByCustomerId(customer_id);
        return ordersByCustomerId.orElseThrow(() -> new OrderNotFoundException("Order Data with customer id " + customer_id + " is not found"));
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") long id) {
        logger.info("/api/v1/order/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> orderById = orderService.getOrderById(id);
        return orderById.orElseThrow(() -> new OrderNotFoundException("Order Data with id " + id + " is not found"));
    }


    @GetMapping("/order-date-time/{order_date_time}")
    public List<Order> getOrdersByOrderDateTime(@PathVariable(value = "order_date_time") LocalDateTime orderDateTime) {
        logger.info("/api/v1/order/order-date-time/{order_date_time} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Order>> orderByOrderDateTime = orderService.getOrdersByOrderDateTime(orderDateTime);
        return orderByOrderDateTime.orElseThrow(() -> new OrderNotFoundException("Order Data with the specified date and time of  " + orderDateTime + " is not found"));
    }

    @GetMapping("/total-price/{total_price}")
    public List<Order> getOrdersByTotalPrice(@PathVariable(value = "total_price") double totalPrice) {
        logger.info("/api/v1/order/total-price/{total_price} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<List<Order>> ordersByTotalPrice = orderService.getOrdersByTotalPrice(totalPrice);
        return ordersByTotalPrice.orElseThrow(() -> new OrderNotFoundException("Order Data with the specified date and time of  " + totalPrice + " is not found"));


    }

    @DeleteMapping("/delete")
    public Order deleteOrder(@RequestBody Order order) {
        logger.info("/api/v1/order/delete is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> orderDeleted = orderService.deleteOrder(order);
        return orderDeleted.orElseThrow(() -> new OrderNotDeletedException("Order Data is not Deleted"));

    }

    @DeleteMapping("/delete/{id}")
    public Order deleteOrderById(@PathVariable("id") long id) {
        logger.info("/api/v1/order/delete/{id} is being accessed!");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        Optional<Order> order = orderService.getOrderById(id);
        if (!order.isPresent() || !order.isEmpty())
            throw new OrderNotFoundException("There is no Order with id " + id + " . Hence, it is not deleted");


        Optional<Order> orderDeleted = orderService.deleteOrderById(id);
        return orderDeleted.orElseThrow(() -> new OrderNotDeletedException("Order Data is not Deleted"));
    }

}
