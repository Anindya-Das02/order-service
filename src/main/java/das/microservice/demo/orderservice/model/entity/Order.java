package das.microservice.demo.orderservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String paymentTxnId;
    private String orderStatus;
}
