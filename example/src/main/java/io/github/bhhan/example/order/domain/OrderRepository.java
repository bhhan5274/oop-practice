package io.github.bhhan.example.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
