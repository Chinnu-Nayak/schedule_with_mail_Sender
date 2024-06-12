package com.chinu.Controller;

import com.chinu.Entity.Order;
import com.chinu.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order bi(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
}
