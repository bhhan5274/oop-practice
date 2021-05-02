package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.common.event.EventPublisher;
import io.github.bhhan.example.order.domain.Order;
import io.github.bhhan.example.order.domain.OrderRepository;
import io.github.bhhan.example.order.domain.OrderValidator;
import io.github.bhhan.example.order.domain.event.OrderDeliveredEvent;
import io.github.bhhan.example.order.domain.event.OrderPayedEvent;
import io.github.bhhan.example.order.usecase.dto.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EventPublisher eventPublisher;

    public Long placeOrder(Cart cart){
        final Order order = orderMapper.mapFrom(cart);
        order.place(orderValidator);
        orderRepository.save(order);

        log.info(String.format("placeOrder[%s] = orderId[%s] >> OrderService", LocalDateTime.now(), order.getId()));

        return order.getId();
    }

    public void payOrder(Long orderId){
        final Order order = findOrder(orderId);
        order.payed();

        eventPublisher.raise(new OrderPayedEvent(orderId));
        log.info(String.format("payOrder[%s] = orderId[%s] >> OrderService", LocalDateTime.now(), orderId));
    }

    public void deliverOrder(Long orderId){
        final Order order = findOrder(orderId);
        order.delivered();

        eventPublisher.raise(new OrderDeliveredEvent(order));
        log.info(String.format("deliverOrder[%s] = orderId[%s] >> OrderService", LocalDateTime.now(), orderId));
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
