package com.online.students.service.API.Orders;

import java.time.LocalDateTime;

public record OrderDTO(Long id, LocalDateTime time, Statuses status, Long idCustomer, Long idAssistance) {
}
