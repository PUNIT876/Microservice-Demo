package com.javahack.orderservice.api.controller;

import com.javahack.orderservice.api.common.Payment;
import com.javahack.orderservice.api.common.TransactionRequest;
import com.javahack.orderservice.api.common.TransactionResponse;
import com.javahack.orderservice.api.entity.Order;
import com.javahack.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) {
        return orderService.saveOrder(transactionRequest);
    }
}
