package com.online.students.service.API.Orders;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getAll();

    OrderEntity getById(Long id);

    OrderEntity create(OrderDTO orderDTO);

    OrderEntity update(Long id, OrderDTO orderDTO);

    OrderEntity changeStatus(Long id, String status, String message);

    boolean delete(Long id);
}
