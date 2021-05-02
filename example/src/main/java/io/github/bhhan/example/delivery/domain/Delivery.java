package io.github.bhhan.example.delivery.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "DELIVERIES")
@Getter
@NoArgsConstructor
public class Delivery {
    public enum DeliveryStatus { DELIVERING, DELIVERED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public static Delivery started(Long orderId){
        return new Delivery(orderId, DeliveryStatus.DELIVERING);
    }

    private Delivery(Long orderId, DeliveryStatus deliveryStatus){
        this(null, orderId, deliveryStatus);
    }

    @Builder
    public Delivery(Long id, Long orderId, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public void complete(){
        this.deliveryStatus = DeliveryStatus.DELIVERED;
    }
}
