package com.online.students.service.Orders;

import com.online.students.service.Assistances.AssistanceEntity;
import com.online.students.service.Users.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity() {}

    public OrderEntity(UserEntity customer, AssistanceEntity assistance) {
        date = LocalDateTime.now();
        this.customer = customer;
        this.assistance = assistance;
        status = Statuses.PENDING;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.ORDINAL)
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
