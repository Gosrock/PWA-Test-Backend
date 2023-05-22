package com.example.pwatestbackend.service;

import com.example.pwatestbackend.domain.User;
import com.example.pwatestbackend.domain.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User register(String fcmToken) {
        return userRepository.save(User.of(fcmToken));
    }

    public User getMyInfo(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUserInfo() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateMyToken(Long id,String fcmToken) {
        User user = userRepository.findById(id).orElseThrow();
        user.updateFcm(fcmToken);
        return user;
    }

    public void sendPushToUser(Long id) {
    }

    public void broadcast() {
    }
}
