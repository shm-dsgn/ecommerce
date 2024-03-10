package com.project.ecommerce.service;

import org.springframework.stereotype.Component;

@Component
public class CreateCodeServiceImpl implements CreateCodeService{

    @Override
    public String createCode() {
        int length = 5;
        return "";
        //Need to return random string of length 5
        //return RandomString.make(length);
    }
}
