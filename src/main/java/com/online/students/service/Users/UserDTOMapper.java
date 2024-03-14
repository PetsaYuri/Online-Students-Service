package com.online.students.service.Users;

import com.online.students.service.Assistances.AssistanceDTOMapper;
import com.online.students.service.Orders.OrderDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<UserEntity, UserDTO> {

    private final AssistanceDTOMapper assistanceDTOMapper;
    private final OrderDTOMapper orderDTOMapper;

    public UserDTOMapper(AssistanceDTOMapper assistanceDTOMapper, OrderDTOMapper orderDTOMapper) {
        this.assistanceDTOMapper = assistanceDTOMapper;
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public UserDTO apply(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                null,
                user.getRole(),
                user.getListOfCreatedAssistance()
                        .stream()
                        .map(assistanceDTOMapper::apply)
                        .toList(),
                user.getListOfOrders()
                        .stream()
                        .map(orderDTOMapper::apply)
                        .toList());
    }
}
