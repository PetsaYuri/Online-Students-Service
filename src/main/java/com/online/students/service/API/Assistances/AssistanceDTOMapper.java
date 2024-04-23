package com.online.students.service.API.Assistances;

import com.online.students.service.API.Orders.OrderDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AssistanceDTOMapper implements Function<AssistanceEntity, AssistanceDTO> {

    private final OrderDTOMapper orderDTOMapper;

    public AssistanceDTOMapper(OrderDTOMapper orderDTOMapper) {
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public AssistanceDTO apply(AssistanceEntity assistanceEntity) {
        return new AssistanceDTO(
                assistanceEntity.getId(),
                assistanceEntity.getTitle(),
                assistanceEntity.getDescription(),
                assistanceEntity.getCost(),
                assistanceEntity.getAssistanceCategory().getId(),
                assistanceEntity.getInstructor().getId(),
                assistanceEntity.getOrders()
                        .stream()
                        .map(orderDTOMapper)
                        .toList());
    }
}
