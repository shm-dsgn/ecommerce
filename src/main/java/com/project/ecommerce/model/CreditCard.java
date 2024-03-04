package com.project.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "expirationDate")
    private String expirationDate;

    @Column(name = "nameAndSurname")
    private String nameAndSurname;

    @ManyToOne
    private User user;

    public CreditCard(String cardNumber, int cvv, String expirationDate, String nameAndSurname) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.nameAndSurname = nameAndSurname;
    }
}
