package com.example.pwatestbackend.controller;


import com.example.pwatestbackend.domain.User;
import com.example.pwatestbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "2. [유저]")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/register/{fcmToken}")
    public User register(@PathVariable("fcmToken") String fcmToken) {
        return userService.register(fcmToken);
    }

    @Operation(summary = "내정보 불러오기")
    @GetMapping("/{userId}/me")
    public User getMyInfo(@PathVariable("userId") Long id) {
        return userService.getMyInfo(id);
    }

    @Operation(summary = "내 fcm 토큰 정보 업데이트 하기")
    @PostMapping ("/{userId}/token/{fcmToken}")
    public User updateMyToken(@PathVariable("userId") Long id,@PathVariable("fcmToken") String fcmToken) {
        return userService.updateMyToken(id,fcmToken);
    }

    @Operation(summary = "유저한테 푸시알림 보내기")
    @PostMapping ("/{userId}/push")
    public void sendPushToUser(@PathVariable("userId") Long id) throws ExecutionException, InterruptedException {
        userService.sendPushToUser(id);
    }

    @Operation(summary = "브로드 캐스팅하기")
    @PostMapping ("/broadcast")
    public void broadcast() throws ExecutionException, InterruptedException {
        userService.broadcast();
    }

    @Operation(summary = "모든 유저 정보 받아오기")
    @GetMapping
    public List<User> getAllUserInfo() {
        return userService.getAllUserInfo();
    }
}
