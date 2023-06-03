package das.microservice.demo.orderservice.model;

import das.microservice.demo.orderservice.annotations.SIA;
import das.microservice.demo.orderservice.model.entity.Order;
import lombok.*;

@SIA
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {
    private String message;
    private String status;
    private String transactionId;
    private Order order;
}
