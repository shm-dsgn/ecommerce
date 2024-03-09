package com.project.ecommerce.controller;

import com.project.ecommerce.dto.viewDto.UserViewDto;
import com.project.ecommerce.model.User;
import com.project.ecommerce.request.UserNameUpdateRequest;
import com.project.ecommerce.request.UserUpdateNotificationPermissionRequest;
import com.project.ecommerce.service.user.UserService;
import com.project.ecommerce.shared.GenericResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;

    // adding new user
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid User user) {
        userService.add(user);
        return ResponseEntity.ok(new GenericResponse("User Created."));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("getById/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("slice")
    public ResponseEntity<List<User>> slice(Pageable pageable) {
        List<User> users = this.userService.slice(pageable);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteById(Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.ok(new GenericResponse("User deleted..."));
    }

    @GetMapping("getDto")
    public ResponseEntity<List<UserViewDto>> getDto() {
        List<UserViewDto> users = userService.getUserViewDto();
        return ResponseEntity.ok(users);
    }

    @PutMapping("updateByUsername")
    public ResponseEntity<?> updateByUserName(@RequestBody UserNameUpdateRequest userNameUpdateRequest) {
        userService.updateByUserName(userNameUpdateRequest.getUserId(), userNameUpdateRequest.getUserName());
        return ResponseEntity.ok("Success");
    }

    @PutMapping("updateUserNotificationPermission")
    public ResponseEntity<?> updateByNotificationPermission(UserUpdateNotificationPermissionRequest request) {
        userService.updateByNotificationPermission(request.getUserId(), request.isPermission());
        return ResponseEntity.ok("Success");
    }

}
