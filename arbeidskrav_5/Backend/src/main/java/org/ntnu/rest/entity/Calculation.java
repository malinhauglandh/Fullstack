package org.ntnu.rest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "calculations")
public class Calculation {
    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "calculation")
    private String calculation;

    public Calculation() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getCalculation() {
        return calculation;
    }
    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }
}