package com.online.students.service.API.AssistanceCategories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceCategoryRepository extends JpaRepository<AssistanceCategoryEntity, Long> {

    AssistanceCategoryEntity findByTitle(String title);
}
