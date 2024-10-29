package com.eximius.eximius.Services;

import com.eximius.eximius.Constant.OrderStatus;
import com.eximius.eximius.Entities.Order;
import com.eximius.eximius.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        // Lógica para crear una nueva orden
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        // Lógica para obtener todas las órdenes de un usuario específico
        return orderRepository.findByUser_Id(userId);
    }

    public Order getOrderById(Long orderId) {
        // Lógica para obtener una orden por su ID
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, String status) {
        // Buscar la orden por su ID
        Order order = getOrderById(orderId);

        // Convertir el String a un enum OrderStatus
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

        // Establecer el estado de la orden
        order.setStatus(orderStatus);

        // Guardar la orden con el nuevo estado
        return orderRepository.save(order);
    }
}
