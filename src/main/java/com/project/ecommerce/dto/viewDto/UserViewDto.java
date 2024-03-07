package com.project.ecommerce.dto.viewDto;

import com.project.ecommerce.model.User;
import java.io.Serializable;

public class UserViewDto implements Serializable {

    private static final Long serialVersionID = 1L;

    private final String userName;
    private final String email;
    private final String password;

    private UserViewDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static UserViewDto of(User user) {
        return new UserViewDto(user.getUserName(), user.getPassword() ,user.getEmail());
    }
}
