package com.online.students.service.Assistances;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceRepository extends JpaRepository<AssistanceEntity, Long> {
}
