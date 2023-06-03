package das.microservice.demo.orderservice.model;

import das.microservice.demo.orderservice.annotations.SIA;
import lombok.*;

@SIA
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private int paymentId;
    private String status;
    private String transactionId;
}
