package com.online.students.service.API.Orders;

import com.online.students.service.API.Assistances.AssistanceEntity;
import com.online.students.service.API.Assistances.AssistanceService;
import com.online.students.service.API.Users.Roles;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        UserEntity customer = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        AssistanceEntity assistance = assistanceService.getById(orderDTO.idAssistance());

        if (customer.getRole().equals(Roles.INSTRUCTOR)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't buy assistance, because you're an " + Roles.INSTRUCTOR);
        }

        OrderEntity order = new OrderEntity(customer, assistance, orderDTO.deadline(), orderDTO.notes());
        userService.changeBalance(customer.getId(), -order.getPrice());
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity update(Long id, OrderDTO orderDTO) {
        OrderEntity existingOrder = getById(id);
        UserEntity seller = existingOrder.getAssistance().getInstructor();
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (seller.equals(currentUser) || currentUser.getRole().equals(Roles.OWNER) || currentUser.getRole().equals(Roles.ADMIN)) {
            if (existingOrder.getStatus().equals(Statuses.NEW)) {
                existingOrder.setDeadline(orderDTO.deadline());
                existingOrder.setNotes(orderDTO.notes());
                return orderRepository.save(existingOrder);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change your details in this order. You are already taking action with this order");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't edit this order, because it's not your order");
    }

    @Override
    public OrderEntity changeStatus(Long id, String status, String message) {
        Statuses updatedStatus = Statuses.valueOf(status);

        switch (updatedStatus) {
            case ACCEPTED -> {
                return accept(id);
            }

            case REFUSED -> {
                return refuse(id);
            }

            case CANCELED -> {
                return cancel(id, message);
            }

            case COMPLETED -> {
                return complete(id, message);
            }

            case CALL_ADMIN -> {
                return callAdmin(id, message);
            }

            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status in your request is incorrect");
        }
    }

    public OrderEntity accept(Long id) {
        OrderEntity existingOrder = getById(id);
        UserEntity seller = existingOrder.getAssistance().getInstructor();
        if (isCurrentUser(seller)) {
            if (existingOrder.getStatus().equals(Statuses.NEW)) {
                existingOrder.setStatus(Statuses.ACCEPTED);
                existingOrder.setMessage("Seller will contact you later");
                return orderRepository.save(existingOrder);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already taking action with this order");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't accept this order, because it's not your order");
    }

    public OrderEntity refuse(Long id) {
        OrderEntity existingOrder = getById(id);
        UserEntity seller = existingOrder.getAssistance().getInstructor();

        if (isCurrentUser(seller)) {
            if (existingOrder.getStatus().equals(Statuses.NEW)) {
                existingOrder.setStatus(Statuses.REFUSED);
                existingOrder.setMessage("Seller refused this request");

                OrderEntity savedOrder = orderRepository.save(existingOrder);
                userService.changeBalance(savedOrder.getCustomer().getId(), savedOrder.getPrice());
                return savedOrder;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already taking action with this order");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't refuse this order, because it's not your order");
    }

    public OrderEntity cancel(Long id, String reason) {
        OrderEntity existingOrder = getById(id);
        UserEntity seller = existingOrder.getAssistance().getInstructor();

        if (isCurrentUser(seller) || isAdminOrOwnerCurrentUser()) {
            if (existingOrder.getStatus().equals(Statuses.ACCEPTED) || isAdminOrOwnerCurrentUser()) {
                existingOrder.setStatus(Statuses.CANCELED);
                existingOrder.setMessage(reason);

                OrderEntity savedOrder = orderRepository.save(existingOrder);
                userService.changeBalance(savedOrder.getCustomer().getId(), existingOrder.getPrice());
                return savedOrder;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't cancel this order. This order don't have " + Statuses.ACCEPTED + " status");
        }


        UserEntity customer = existingOrder.getCustomer();

        if (isCurrentUser(customer)) {
            if (existingOrder.getStatus().equals(Statuses.NEW)) {
                existingOrder.setStatus(Statuses.CANCELED);
                existingOrder.setMessage(reason);
                OrderEntity savedOrder = orderRepository.save(existingOrder);
                userService.changeBalance(savedOrder.getCustomer().getId(), existingOrder.getPrice());
                return savedOrder;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't cancel this order. You are already taking action with this order");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't do anything with this order, because it's not your order");
    }

    public OrderEntity complete(Long id, String comment) {
        OrderEntity existingOrder = getById(id);
        UserEntity customer = existingOrder.getCustomer();

        if (isCurrentUser(customer) || isAdminOrOwnerCurrentUser()) {
            if (existingOrder.getStatus().equals(Statuses.ACCEPTED) || isAdminOrOwnerCurrentUser()) {
                existingOrder.setStatus(Statuses.COMPLETED);
                existingOrder.setMessage(comment);

                OrderEntity savedOrder = orderRepository.save(existingOrder);
                userService.changeBalance(savedOrder.getAssistance().getInstructor().getId(), savedOrder.getPrice());
                return savedOrder;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't mark this order as done. This order don't have " + Statuses.ACCEPTED + " status");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't do anything with this order, because it's not your order");
    }

    public OrderEntity callAdmin(Long id, String complaint) {
        OrderEntity existingOrder = getById(id);
        UserEntity seller = existingOrder.getAssistance().getInstructor();
        UserEntity customer = existingOrder.getCustomer();

        if (isCurrentUser(seller) || isCurrentUser(customer)) {
            if (existingOrder.getStatus().equals(Statuses.ACCEPTED)) {
                existingOrder.setStatus(Statuses.CALL_ADMIN);
                existingOrder.setMessage(complaint);
                return orderRepository.save(existingOrder);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't call admin for this order, because the status of this order is not " + Statuses.ACCEPTED);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cannot call admin for this order, because it's not your order");
    }

    boolean isAdminOrOwnerCurrentUser() {
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (currentUser.getRole().equals(Roles.OWNER) || currentUser.getRole().equals(Roles.ADMIN)) {
            return true;
        }
        return false;
    }

    boolean isCurrentUser(UserEntity customer) {
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (currentUser.equals(customer)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        OrderEntity order = orderRepository.getReferenceById(id);
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (currentUser.getRole().equals(Roles.OWNER) || currentUser.getRole().equals(Roles.ADMIN)) {
            orderRepository.delete(order);
            return true;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't delete this order, because you don't have special access");
    }
}
