package com.codegym.controller;

import com.codegym.model.Order;
import com.codegym.model.Status;
import com.codegym.repository.OrderRepository;
import com.codegym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listOder(){
        List<Order> orders = (List<Order>) orderService.findAll();
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOder(@PathVariable Long id){
        Optional<Order> oder = orderService.findById(id);
        if (!oder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oder, HttpStatus.OK);
    }

    @GetMapping("/order/user/{id}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id) {
        List<Order> orders = orderService.findAllByUser_Id(id);
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        order.setStatus(Status.normal);
        orderService.save(order);
        return new ResponseEntity<Long>(order.getId(), HttpStatus.CREATED);
    }


    @PutMapping("order/{id}")
    public ResponseEntity<?> updateOder(@Valid @RequestBody Order order, @PathVariable Long id){
        Optional<Order> oder1 = orderService.findById(id);
        if(!oder1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oder1.get().setDate(order.getDate());
        oder1.get().setDeliveryAddress(order.getDeliveryAddress());

        orderService.save(oder1.get());
        return new ResponseEntity<>(oder1, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOder(@PathVariable Long id){
        Optional<Order> oder = orderService.findById(id);
        if (!oder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(id);
        return new ResponseEntity<>(oder, HttpStatus.OK);
    }

    @GetMapping("order/cart/{id}")
    public ResponseEntity<Order> findByStatusAndUser_Id(@PathVariable("id") Long id) {
        Status status = Status.normal;
        Order order = orderRepository.findByStatusAndUser_Id(status, id);
        if (order != null) {
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/order/toOrder")
    public ResponseEntity<?> orderToOrder(@RequestBody Order order) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Optional<Order> currentOrder = orderService.findById(order.getId());
        if (currentOrder.isPresent()) {
            currentOrder.get().setDate(date);
            currentOrder.get().setStatus(Status.order);
            currentOrder.get().setTotal(order.getTotal());
            currentOrder.get().setPhone(order.getPhone());
            currentOrder.get().setDeliveryAddress(order.getDeliveryAddress());
            orderService.save(currentOrder.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/order/change-status/{id}")
    public ResponseEntity<?> changeOrderStatus(@RequestBody String status, @PathVariable Long id) {
        Status currentStatus;
        switch (status) {
            case "order":
                currentStatus = Status.order;
                break;
            case "processing":
                currentStatus = Status.processing;
                break;
            case "Cancel":
                currentStatus = Status.Cancel;
                break;
            case "Done":
                currentStatus = Status.Done;
                break;
            case "normal":
                currentStatus = Status.normal;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        Optional<Order> currentOrder = orderService.findById(id);
        if (currentOrder.isPresent()) {
            currentOrder.get().setStatus(currentStatus);
            orderService.save(currentOrder.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
