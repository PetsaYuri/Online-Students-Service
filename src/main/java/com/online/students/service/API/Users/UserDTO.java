package com.online.students.service.API.Users;

import com.online.students.service.API.Articles.ArticleDTO;
import com.online.students.service.API.Assistances.AssistanceDTO;
import com.online.students.service.API.Orders.OrderDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDTO(Long id,
                      @NotNull @NotBlank String fullName,
                      @NotNull @NotBlank String email,
                      @NotNull @NotBlank String password,
                      int balance,
                      String image,
                      String role,
                      List<AssistanceDTO> listOfCreatedAssistance,
                      List<OrderDTO> listOfOrders,
                      List<ArticleDTO> articleList) {
}
