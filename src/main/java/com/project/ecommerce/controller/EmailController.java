package com.project.ecommerce.controller;

import com.project.ecommerce.request.PushAllUserEmailRequest;
import com.project.ecommerce.request.PushEmailRequest;
import com.project.ecommerce.service.SendEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email/")
@CrossOrigin
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailService sendEmailService;

    @PostMapping("pushEmail")
    public ResponseEntity<?> pushEmail(@Valid @RequestBody PushEmailRequest pushEmailRequest) {
        sendEmailService.sendEmails(pushEmailRequest.getEmail(), pushEmailRequest.getBody(), pushEmailRequest.getTitle());
        return ResponseEntity.ok("success");
    }

    @PostMapping("pushEmailToAllUser")
    public ResponseEntity<?> pushAllUser(@Valid @RequestBody PushAllUserEmailRequest pushAllUserEmailRequest) {
        sendEmailService.sendEmailAllUser(pushAllUserEmailRequest.getBody(), pushAllUserEmailRequest.getTitle());
        return ResponseEntity.ok("success");
    }
}
