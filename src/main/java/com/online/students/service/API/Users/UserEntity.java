package com.online.students.service.API.Users;

import com.online.students.service.API.Articles.ArticleEntity;
import com.online.students.service.API.Orders.OrderEntity;
import com.online.students.service.API.Assistances.AssistanceEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    public UserEntity(){}

    public UserEntity(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        role = Roles.STUDENT;
        listOfCreatedAssistance = new ArrayList<>();
        listOfOrders = new ArrayList<>();
        listOfArticles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName, email, password;

    @Column
    private String image;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "instructor")
    private List<AssistanceEntity> listOfCreatedAssistance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private List<OrderEntity> listOfOrders;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "creator")
    private List<ArticleEntity> listOfArticles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<AssistanceEntity> getListOfCreatedAssistance() {
        return listOfCreatedAssistance;
    }

    public void setListOfCreatedAssistance(List<AssistanceEntity> listOfCreatedAssistance) {
        this.listOfCreatedAssistance = listOfCreatedAssistance;
    }

    public List<OrderEntity> getListOfOrders() {
        return listOfOrders;
    }

    public void setListOfOrders(List<OrderEntity> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public List<ArticleEntity> getListOfArticles() {
        return listOfArticles;
    }

    public void setListOfArticles(List<ArticleEntity> listOfArticles) {
        this.listOfArticles = listOfArticles;
    }
}
