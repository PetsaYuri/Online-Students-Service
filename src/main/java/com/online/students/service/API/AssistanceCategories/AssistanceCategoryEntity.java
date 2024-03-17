package com.online.students.service.API.AssistanceCategories;

import com.online.students.service.API.Assistances.AssistanceEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories_of_assistance")
public class AssistanceCategoryEntity {

    public AssistanceCategoryEntity() {}

    public AssistanceCategoryEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title, description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "assistanceCategory")
    private List<AssistanceEntity> assistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
