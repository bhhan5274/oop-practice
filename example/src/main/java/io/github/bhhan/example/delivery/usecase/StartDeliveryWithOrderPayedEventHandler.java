package io.github.bhhan.example.delivery.usecase;

import io.github.bhhan.example.delivery.domain.Delivery;
import io.github.bhhan.example.delivery.domain.DeliveryRepository;
import io.github.bhhan.example.order.domain.event.OrderPayedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDeliveryWithOrderPayedEventHandler {
    private final DeliveryRepository deliveryRepository;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void handle(OrderPayedEvent event){
        final Delivery delivery = Delivery.started(event.getOrderId());
        deliveryRepository.save(delivery);

        log.info(String.format("OrderPayedEvent[%s] = orderId[%s] >> StartDeliveryWithOrderPayedEventHandler", LocalDateTime.now(), event.getOrderId()));
    }
}
