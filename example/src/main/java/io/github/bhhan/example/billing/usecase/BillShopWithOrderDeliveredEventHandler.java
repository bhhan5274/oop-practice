package io.github.bhhan.example.billing.usecase;

import io.github.bhhan.example.billing.domain.Billing;
import io.github.bhhan.example.billing.domain.BillingRepository;
import io.github.bhhan.example.order.domain.event.OrderDeliveredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class BillShopWithOrderDeliveredEventHandler {
    private final BillingRepository billingRepository;
    private final BillingShopProxy billingShopProxy;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = OrderDeliveredEvent.class)
    public void handle(OrderDeliveredEvent event){
        final Billing billing = billingRepository.findByShopId(event.getShopId())
                .orElseGet(() -> billingRepository.save(new Billing(event.getShopId())));

        billing.billCommissionFee(billingShopProxy.calculateCommissionFee(event.getShopId(), event.getTotalPrice()));
        log.info(String.format("OrderDeliveredEvent[%s] = orderId[%s] >> BillShopWithOrderDeliveredEventHandler", LocalDateTime.now(), event.getOrderId()));
    }
}
