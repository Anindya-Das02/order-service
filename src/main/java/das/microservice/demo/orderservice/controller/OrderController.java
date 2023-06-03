package das.microservice.demo.orderservice.controller;

import das.microservice.demo.orderservice.model.OrderResponse;
import das.microservice.demo.orderservice.model.entity.Order;
import das.microservice.demo.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> index(){
        log.info("inside OrderController.index");
        return ResponseEntity.ok(Map.of("message","order up & running"));
    }

    @PostMapping("/bookOrder")
    @ResponseBody
    public OrderResponse bookOrder(RequestEntity<Order> orderRequestEntity){
        log.info("invoked bookOrder service");
        HttpHeaders headers = orderRequestEntity.getHeaders();
        Order order = orderRequestEntity.getBody();
        return orderService.saveOrder(order);
    }
}
