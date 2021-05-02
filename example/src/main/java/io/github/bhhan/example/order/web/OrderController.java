package io.github.bhhan.example.order.web;

import io.github.bhhan.example.order.usecase.OrderService;
import io.github.bhhan.example.order.usecase.dto.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long placeOrder(@RequestBody Cart cart){
        return orderService.placeOrder(cart);
    }

    @PostMapping("/{orderId}/payed")
    @ResponseStatus(HttpStatus.OK)
    public void payOrder(@PathVariable Long orderId){
        orderService.payOrder(orderId);
    }

    @PostMapping("/{orderId}/delivered")
    @ResponseStatus(HttpStatus.OK)
    public void deliverOrder(@PathVariable Long orderId){
        orderService.deliverOrder(orderId);
    }
}
