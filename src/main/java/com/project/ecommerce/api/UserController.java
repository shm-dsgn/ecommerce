package com.project.ecommerce.api;

import com.project.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAll() {

        return null;
    }

}
