package fosi.taco_cloud.service;

import fosi.taco_cloud.repository.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService
{
    private final OrderRepository orderRepository;

    public OrderAdminService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders()
    {
        orderRepository.deleteAll();
    }
}
