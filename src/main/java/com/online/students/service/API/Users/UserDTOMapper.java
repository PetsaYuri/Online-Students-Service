package com.online.students.service.API.Users;

import com.online.students.service.API.Articles.ArticleDTOMapper;
import com.online.students.service.API.Assistances.AssistanceDTOMapper;
import com.online.students.service.API.Orders.OrderDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<UserEntity, UserDTO> {

    private final AssistanceDTOMapper assistanceDTOMapper;
    private final OrderDTOMapper orderDTOMapper;
    private final ArticleDTOMapper articleDTOMapper;

    public UserDTOMapper(AssistanceDTOMapper assistanceDTOMapper, OrderDTOMapper orderDTOMapper, ArticleDTOMapper articleDTOMapper) {
        this.assistanceDTOMapper = assistanceDTOMapper;
        this.orderDTOMapper = orderDTOMapper;
        this.articleDTOMapper = articleDTOMapper;
    }

    @Override
    public UserDTO apply(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                null,
                user.getBalance(),
                user.getImage(),
                user.getRole().name(),

                user.getListOfCreatedAssistance()
                        .stream()
                        .map(assistanceDTOMapper::apply)
                        .toList(),

                user.getListOfOrders()
                        .stream()
                        .map(orderDTOMapper::apply)
                        .toList(),

                user.getListOfArticles()
                        .stream()
                        .map(articleDTOMapper)
                        .toList());
    }
}
