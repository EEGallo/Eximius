package com.eximius.eximius.Repositories;

import com.eximius.eximius.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
