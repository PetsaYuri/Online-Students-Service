package com.online.students.service.API.Orders;

import java.time.LocalDateTime;

public record OrderDTO(Long id,
                       LocalDateTime dateOfCreation,
                       LocalDateTime deadline,
                       String notes,
                       String message,
                       int price,
                       Statuses status,
                       Long idCustomer,
                       Long idAssistance) {
}
