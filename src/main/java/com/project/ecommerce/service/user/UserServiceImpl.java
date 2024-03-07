package com.project.ecommerce.service.user;

import com.project.ecommerce.dto.viewDto.UserViewDto;
import com.project.ecommerce.exception.NotFoundException;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.request.UserDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User add(User userCreateDto) {
        userRepository.save(new User(userCreateDto.getUserName(),
                userCreateDto.getPassword(), userCreateDto.getEmail(), userCreateDto.getUserCreateDate(), userCreateDto.isNotificationPermission()));

        return userCreateDto;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User couldn't be found by following id: " + id));
    }

    @Override
    public List<User> slice(Pageable pageable) {
        return this.userRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserViewDto> getUserViewDto() {
        return userRepository.findAll().stream().map(UserViewDto::of).collect(Collectors.toList());
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void authDeleteByUser(UserDeleteRequest userDeleteRequest) {
        User user = userRepository.findByEmail(userDeleteRequest.getEmail());
        userRepository.deleteById(user.getId());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateByUserName(Long userId, String userName) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setUserName(userName);
            userRepository.save(user.get());
        }
    }

    @Override
    public void updateByNotificationPermission(Long userId, boolean permission) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setNotificationPermission(permission);
            userRepository.save(user.get());
        }
    }
}
