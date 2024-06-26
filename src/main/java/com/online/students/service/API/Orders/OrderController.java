package com.online.students.service.API.Orders;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public static final String URI_ORDERS_ID = "/{id}";
    public static final String URI_CHANGE_STATUS = URI_ORDERS_ID + "/status";
    public static final String URI_OWN_ORDERS = "/own";
    public static final String URI_STATUSES = "/statuses";

    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;

    public OrderController(OrderService orderService, OrderDTOMapper orderDTOMapper) {
        this.orderService = orderService;
        this.orderDTOMapper = orderDTOMapper;
    }

    @GetMapping
    public List<OrderDTO> getAll(@RequestParam(required = false) String status) {
            return orderService.getAll(status)
                    .stream()
                    .map(orderDTOMapper)
                    .toList();
    }

    @GetMapping(URI_OWN_ORDERS)
    public List<OrderDTO> getOwnOrders(@RequestParam(required = false) String status) {
        return orderService.getOwnOrders(status)
                .stream()
                .map(orderDTOMapper)
                .toList();
    }

    @GetMapping(URI_STATUSES)
    public List<Statuses> getStatuses() {
        return orderService.getStatuses();
    }

    @GetMapping(URI_ORDERS_ID)
    public OrderDTO getById(@PathVariable Long id) {
        return orderDTOMapper.apply(orderService.getById(id));
    }

    @PostMapping
    public OrderDTO create(@RequestBody @Valid OrderDTO orderDTO) {
        return orderDTOMapper.apply(orderService.create(orderDTO));
    }

    @PutMapping(URI_ORDERS_ID)
    public OrderDTO update(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO) {
        return orderDTOMapper.apply(orderService.update(id, orderDTO));
    }

    @PatchMapping(URI_CHANGE_STATUS)
    public OrderDTO changeStatus(@PathVariable Long id, @RequestParam String status, @RequestParam(required = false) String message) {
        return orderDTOMapper.apply(orderService.changeStatus(id, status, message));
    }

    @DeleteMapping(URI_ORDERS_ID)
    public boolean delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}
