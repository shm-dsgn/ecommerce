package com.project.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{com.eCommerceProject.notnull.username.message}")
    @Column(name = "username")
    private String userName;

    @NotNull(message = "{com.eCommerceProject.notnull.password.message}")
    @Column(name = "password")
    private String password;

    @Email(message = "{com.eCommerceProject.email.message}")
    @Column(name = "email")
    private String email;

    @Column(name = "createDate")
    private Date userCreateDate;

    @Column(name = "notificationPermission")
    private boolean notificationPermission = true;

    @OneToMany
    private List<SellerComment> sellerComment;

    @OneToMany
    private List<ProductComment> productComment;

    @ManyToOne
    private CreditCard creditCard;

    @OneToMany
    private List<Address> address;

    @OneToMany
    private List<PromoCode> promoCode;

    public User(String userName, String password, String email, Date userCreateDate, boolean notificationPermission) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userCreateDate = userCreateDate;
        this.notificationPermission = notificationPermission;
    }
}
