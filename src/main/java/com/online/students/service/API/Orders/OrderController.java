package com.online.students.service.API.Orders;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final String URI_ORDERS_ID = "/{id}";

    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;

    public OrderController(OrderService orderService, OrderDTOMapper orderDTOMapper) {
        this.orderService = orderService;
        this.orderDTOMapper = orderDTOMapper;
    }

    @GetMapping
    public List<OrderDTO> getAll() {
            return orderService.getAll()
                    .stream()
                    .map(orderDTOMapper::apply)
                    .toList();
    }

    @GetMapping(URI_ORDERS_ID)
    public OrderDTO getById(@PathVariable Long id) {
        return orderDTOMapper.apply(orderService.getById(id));
    }

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        return orderDTOMapper.apply(orderService.create(orderDTO));
    }

    @DeleteMapping(URI_ORDERS_ID)
    public boolean delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}
