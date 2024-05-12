package com.online.students.service.API.Orders;

import com.online.students.service.API.Assistances.AssistanceEntity;
import com.online.students.service.API.Users.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity() {}

    public OrderEntity(UserEntity customer, AssistanceEntity assistance, LocalDateTime deadline, String notes) {
        dateOfCreation = LocalDateTime.now();
        this.deadline = deadline;
        this.notes = notes;
        this.price = assistance.getCost();
        this.customer = customer;
        this.assistance = assistance;
        status = Statuses.NEW;
        message = "Seller will review your application later";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateOfCreation, deadline;

    @Column(nullable = false)
    private String notes, message;

    @Column(nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private Statuses status;

    @ManyToOne(optional = false)
    private UserEntity customer;

    @ManyToOne(optional = false)
    private AssistanceEntity assistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public AssistanceEntity getAssistance() {
        return assistance;
    }

    public void setAssistance(AssistanceEntity assistance) {
        this.assistance = assistance;
    }
}
