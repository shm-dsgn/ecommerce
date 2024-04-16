package com.project.ecommerce.controller;

import com.project.ecommerce.model.User;
import com.project.ecommerce.request.UserCreateRequest;
import com.project.ecommerce.request.UserDeleteRequest;
import com.project.ecommerce.request.UserLoginRequest;
import com.project.ecommerce.security.JwtTokenProvider;
import com.project.ecommerce.service.SendEmailService;
import com.project.ecommerce.service.user.UserService;
import com.project.ecommerce.shared.ECommerceMessage;
import jakarta.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final SendEmailService sendEmailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.authenticationManager =authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getByUserName(loginRequest.getUserName());
        sendEmailService.sendEmails(user.getEmail(), ECommerceMessage.LOGIN_BODY, ECommerceMessage.LOGIN_TOPIC);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequest user) throws AddressException {
        if(userService.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(ECommerceMessage.EMAIL_ALREADY_IN_USE, HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUserCreateDate(new Date());
        userService.add(newUser);
        sendEmailService.sendEmails(String.valueOf(user.getEmail()), ECommerceMessage.REGISTER_BODY, ECommerceMessage.REGISTER_TOPIC + ECommerceMessage.REGISTER_TOPIC_EMOJI);
        return new ResponseEntity<>(ECommerceMessage.USER_CREATED, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteByUser")
    public ResponseEntity<?> deleteByUser(@RequestBody UserDeleteRequest userDeleteRequest) {
        userService.authDeleteByUser(userDeleteRequest);
        sendEmailService.sendEmails(userDeleteRequest.getEmail(), ECommerceMessage.AUTH_DELETE_BODY, ECommerceMessage.AUTH_DELETE_TOPIC + ECommerceMessage.AUTH_DELETE_TOPIC_EMOJI);
        return new ResponseEntity<>(ECommerceMessage.USER_DELETED, HttpStatus.OK);
    }
}
