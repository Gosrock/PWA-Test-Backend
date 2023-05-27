package com.example.pwatestbackend.service;

import com.example.pwatestbackend.domain.User;
import com.example.pwatestbackend.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.example.pwatestbackend.external.fcm.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FcmService fcmService;
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

    @Transactional
    public void sendPushToUser(Long id) throws ExecutionException, InterruptedException {
        Optional<User> user= userRepository.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException());
        if(user.isPresent()) {
            fcmService.sendUser(user.get());
        }

    }

    @Transactional
    public void broadcast() throws ExecutionException, InterruptedException {
        List<User> users= userRepository.findAll();
        if(!users.isEmpty()){
            fcmService.sendAllUser(users.stream().toList());
        }
    }
}
