package com.example.pwatestbackend.external.fcm;

import com.example.pwatestbackend.domain.User;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FcmService {

    public void sendUser (User user) throws InterruptedException, ExecutionException {
        Message message = Message.builder()
                .setToken(user.getFcmToken())
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(user.getId().toString()+"에게 보내는 알림", "메세지내용입니다"))
                        .build())
                .build();

        send(message,user);
    }

    public void sendAllUser (List<User> users) throws InterruptedException, ExecutionException {

        for( User user : users){
            Message message = Message.builder()
                    .setToken(user.getFcmToken())
                    .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                            .setNotification(new WebpushNotification(user.getId().toString()+"에게 보내는 알림", "메세지내용입니다"))
                            .build())
                    .build();

            send(message,user);
        }
    }
    public void send(Message message, User user) {
        try {
            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            log.info("Sent message: " + response);
        } catch (InterruptedException | ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof FirebaseMessagingException fme) {
                log.error("Firebase Notification Failed: " + fme.getMessage());
                log.info("Firebase Notification token for the user: " + user.getId() + ", errorCodeName: " + fme.getErrorCode().toString());
                if (fme.getErrorCode().toString().equals("INVALID_ARGUMENT") || fme.getErrorCode().toString().equals("NOT_FOUND") || fme.getErrorCode().toString().equals("UNREGISTERED")) {
                    user.clearFcm();
                    log.info("Deleted Firebase Notification token for the user: " + user.getId());
                }
            } else {
                log.error("Unexpected error: " + e.getMessage());
            }
        }
    }

}
