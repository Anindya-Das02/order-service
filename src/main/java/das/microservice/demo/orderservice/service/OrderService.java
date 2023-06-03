package das.microservice.demo.orderservice.service;

import das.microservice.demo.orderservice.client.PaymentClient;
import das.microservice.demo.orderservice.model.OrderResponse;
import das.microservice.demo.orderservice.model.Payment;
import das.microservice.demo.orderservice.model.PaymentTransactionRequest;
import das.microservice.demo.orderservice.model.entity.Order;
import das.microservice.demo.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;

    public OrderResponse saveOrder(Order order){
        log.info("saving order into repo");
        PaymentTransactionRequest paymentTransactionRequest = new PaymentTransactionRequest();
        paymentTransactionRequest.setTransactionId(UUID.randomUUID().toString());
        Payment payment = paymentClient.makePayment(paymentTransactionRequest);
        if(payment == null){
            log.info("[ERROR] payment response is null, returning");
            return null;
        }
        order.setOrderStatus(payment.getStatus());
        order.setPaymentTxnId(payment.getTransactionId());
        orderRepository.save(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setMessage("order placed");
        orderResponse.setTransactionId(UUID.randomUUID().toString());
        orderResponse.setOrder(order);
        return orderResponse;
    }



}
