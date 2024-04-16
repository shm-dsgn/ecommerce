package com.project.ecommerce.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class SellerCreateRequest {

    @NotNull
    private String name;

    private String profilePictureUrl;

    @NotNull
    @Email
    private String email;

    private Date createDate;

    public SellerCreateRequest(String name, String profilePictureUrl, String email, Date createDate) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.createDate = createDate;
    }
}
