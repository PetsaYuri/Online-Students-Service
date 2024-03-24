package com.online.students.service.API.Users;

import com.online.students.service.API.Assistances.AssistanceDTOMapper;
import com.online.students.service.API.Orders.OrderDTOMapper;
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
                user.getImage(),
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
