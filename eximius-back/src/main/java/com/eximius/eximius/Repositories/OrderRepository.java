package com.eximius.eximius.Repositories;

import com.eximius.eximius.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Método para encontrar todas las órdenes de un usuario específico
    List<Order> findByUserId(Long userId);
}

