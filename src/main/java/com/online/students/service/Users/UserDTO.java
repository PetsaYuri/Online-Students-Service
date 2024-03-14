package com.online.students.service.Users;

import com.online.students.service.Assistances.AssistanceDTO;
import com.online.students.service.Assistances.AssistanceEntity;
import com.online.students.service.Orders.OrderDTO;
import com.online.students.service.Orders.OrderEntity;

import java.util.List;

public record UserDTO(Long id,
                      String fullName,
                      String email,
                      String password,
                      Roles role,
                      List<AssistanceDTO> listOfCreatedAssistance,
                      List<OrderDTO> listOfOrders) {
}
