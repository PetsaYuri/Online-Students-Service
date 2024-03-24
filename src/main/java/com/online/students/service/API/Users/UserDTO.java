package com.online.students.service.API.Users;

import com.online.students.service.API.Assistances.AssistanceDTO;
import com.online.students.service.API.Orders.OrderDTO;

import java.util.List;

public record UserDTO(Long id,
                      String fullName,
                      String email,
                      String password,
                      String image,
                      Roles role,
                      List<AssistanceDTO> listOfCreatedAssistance,
                      List<OrderDTO> listOfOrders) {
}
