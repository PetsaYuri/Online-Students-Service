package com.online.students.service.Orders;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getAll();

    OrderEntity getById(Long id);

    OrderEntity create(OrderDTO orderDTO);

    boolean delete(Long id);
}
