package com.online.students.service.Orders;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDTOMapper implements Function<OrderEntity, OrderDTO> {

    @Override
    public OrderDTO apply(OrderEntity orderEntity) {
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getDate(),
                orderEntity.getStatus(),
                orderEntity.getCustomer().getId(),
                orderEntity.getAssistance().getId());
    }
}
