package com.online.students.service.Assistances;

import java.util.List;

public interface AssistanceService {

    List<AssistanceEntity> getAll();

    AssistanceEntity getById(Long id);

    AssistanceEntity create(AssistanceDTO assistanceDTO);

    boolean delete(Long id);
}
