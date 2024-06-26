package com.online.students.service.API.Assistances;

import com.online.students.service.API.Orders.OrderEntity;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.AssistanceCategories.AssistanceCategoryEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assistance")
public class AssistanceEntity {

    public AssistanceEntity() {}

    public AssistanceEntity(String title, String description, int cost, AssistanceCategoryEntity assistanceCategory, UserEntity instructor) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.assistanceCategory = assistanceCategory;
        this.instructor = instructor;
        orders = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title, description;

    @Column(nullable = false)
    private int cost;

    @ManyToOne(optional = false)
    private AssistanceCategoryEntity assistanceCategory;

    @ManyToOne(optional = false)
    private UserEntity instructor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "assistance")
    private List<OrderEntity> orders;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AssistanceCategoryEntity getAssistanceCategory() {
        return assistanceCategory;
    }

    public void setAssistanceCategory(AssistanceCategoryEntity assistanceCategory) {
        this.assistanceCategory = assistanceCategory;
    }

    public UserEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(UserEntity instructor) {
        this.instructor = instructor;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
