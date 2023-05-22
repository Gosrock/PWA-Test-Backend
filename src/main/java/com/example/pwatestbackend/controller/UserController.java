package com.example.pwatestbackend.controller;


import com.example.pwatestbackend.domain.User;
import com.example.pwatestbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
//@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "2. [유저]")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/register")
    public User register() {
        return userService.register();
    }

    @Operation(summary = "내정보 불러오기")
    @GetMapping("/{userId}/me")
    public User getMyInfo(@RequestParam("userId") Long id) {
        return userService.getMyInfo(id);
    }

    @Operation(summary = "내 fcm 토큰 정보 업데이트 하기")
    @PostMapping ("/{userId}/token")
    public User updateMyToken(@RequestParam("userId") Long id) {
        return userService.updateMyToken(id);
    }

    @Operation(summary = "내 fcm 토큰 정보 업데이트 하기")
    @PostMapping ("/{userId}/push")
    public User sendPushToUser(@RequestParam("userId") Long id) {
        return userService.sendPushToUser(id);
    }

    @Operation(summary = "내 fcm 토큰 정보 업데이트 하기")
    @PostMapping ("/broadcast")
    public User broadcast(@RequestParam("userId") Long id) {
        return userService.updateMyToken(id);
    }

    @Operation(summary = "내 fcm 토큰 정보 업데이트 하기")
    @GetMapping
    public List<User> getAllUserInfo() {
        return userService.getAllUserInfo();
    }
}
