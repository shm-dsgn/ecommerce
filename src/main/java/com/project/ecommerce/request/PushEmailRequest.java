package com.project.ecommerce.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PushEmailRequest {

    @Email
    private String email;

    @NotNull
    private String body;

    @NotNull
    private String title;
}
