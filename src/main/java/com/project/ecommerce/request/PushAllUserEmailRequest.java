package com.project.ecommerce.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PushAllUserEmailRequest {

    @NotNull
    private String body;

    @NotNull
    private String title;
}
