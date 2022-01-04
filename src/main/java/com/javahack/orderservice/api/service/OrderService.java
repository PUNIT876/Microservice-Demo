package com.javahack.orderservice.api.service;

import com.javahack.orderservice.api.common.Payment;
import com.javahack.orderservice.api.common.TransactionRequest;
import com.javahack.orderservice.api.common.TransactionResponse;
import com.javahack.orderservice.api.entity.Order;
import com.javahack.orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payment/doPayment", payment, Payment.class);
        String message = (paymentResponse.getPaymentStatus().equals("success")) ? "Payment Processing Successfull" : "Payment Failed";
        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(), paymentResponse.getTransactionId(), message);
    }
}
