package com.project.ecommerce.request;

import lombok.Data;

@Data
public class UserNameUpdateRequest {
    private Long userId;
    private String userName;
}
