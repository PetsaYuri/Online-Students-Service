package com.online.students.service.API.Users;

import com.online.students.service.API.Articles.ArticleDTO;
import com.online.students.service.API.Articles.ArticleEntity;
import com.online.students.service.API.Assistances.AssistanceDTO;
import com.online.students.service.API.Orders.OrderDTO;

import java.util.List;

public record UserDTO(Long id,
                      String fullName,
                      String email,
                      String password,
                      int balance,
                      String image,
                      String role,
                      List<AssistanceDTO> listOfCreatedAssistance,
                      List<OrderDTO> listOfOrders,
                      List<ArticleDTO> articleList) {
}
