package das.microservice.demo.orderservice.client;

import das.microservice.demo.orderservice.model.Payment;
import das.microservice.demo.orderservice.model.PaymentTransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PaymentClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public Payment makePayment(PaymentTransactionRequest paymentTransactionRequest){
        ServiceInstance serviceInstance = loadBalancerClient.choose("PAYMENT-SERVICE");
        String paymentUrl = serviceInstance.getUri().toString() +"/api/payment/pay";
        log.debug("payment url : {}",paymentUrl);
        return restTemplate.postForObject(paymentUrl, paymentTransactionRequest, Payment.class);
    }
}
