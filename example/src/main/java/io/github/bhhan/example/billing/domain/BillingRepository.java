package io.github.bhhan.example.billing.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findByShopId(Long shopId);
}
