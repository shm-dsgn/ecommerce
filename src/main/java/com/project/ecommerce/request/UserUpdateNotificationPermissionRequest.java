package com.project.ecommerce.request;

import lombok.Data;

@Data
public class UserUpdateNotificationPermissionRequest {
    private Long userId;
    private boolean permission;
}
