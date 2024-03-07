package com.project.ecommerce.service.user;

import com.project.ecommerce.dto.viewDto.UserViewDto;
import com.project.ecommerce.model.User;
import com.project.ecommerce.request.UserDeleteRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User add(User createDto);
    List<User> getAll();
    User getById(Long id);
    List<User> slice(Pageable pageable);
    void deleteById(Long id);
    List<UserViewDto> getUserViewDto();
    User getByUserName(String userName);
    void authDeleteByUser(UserDeleteRequest userDeleteRequest);
    User findByEmail(String email);
    void updateByUserName(Long userId, String userName);
    void updateByNotificationPermission(Long userId, boolean permission);
}
