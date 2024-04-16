package com.project.ecommerce.service.user;

import com.project.ecommerce.model.User;
import com.project.ecommerce.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userService.getById(id);
        return JwtUserDetails.create(user);
    }

}
