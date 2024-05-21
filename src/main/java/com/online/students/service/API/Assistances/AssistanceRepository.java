package com.online.students.service.API.Assistances;

import com.online.students.service.API.AssistanceCategories.AssistanceCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceRepository extends JpaRepository<AssistanceEntity, Long> {

    List<AssistanceEntity> findByAssistanceCategory(AssistanceCategoryEntity assistanceCategory);
}
