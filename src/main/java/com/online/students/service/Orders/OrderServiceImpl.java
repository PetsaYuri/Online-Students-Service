package com.online.students.service.Orders;

import com.online.students.service.Assistances.AssistanceEntity;
import com.online.students.service.Assistances.AssistanceService;
import com.online.students.service.Users.UserEntity;
import com.online.students.service.Users.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AssistanceService assistanceService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, AssistanceService assistanceService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.assistanceService = assistanceService;
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getById(Long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public OrderEntity create(OrderDTO orderDTO) {
        UserEntity customer = userService.getOneById(orderDTO.idCustomer());
        AssistanceEntity assistance = assistanceService.getById(orderDTO.idAssistance());

        OrderEntity order = new OrderEntity(customer, assistance);
        return orderRepository.save(order);
    }

    @Override
    public boolean delete(Long id) {
        OrderEntity order = orderRepository.getReferenceById(id);
        orderRepository.delete(order);
        return true;
    }
}
