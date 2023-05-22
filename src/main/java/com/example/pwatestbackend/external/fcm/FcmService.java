package com.example.pwatestbackend.external.fcm;

import com.example.pwatestbackend.domain.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FcmService {
   // private static final Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendUser (User user) throws InterruptedException, ExecutionException {
        Message message = Message.builder()
                .setToken(user.getFcmToken())
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(user.getId().toString()+"에게 보내는 알림", "메세지내용입니다"))
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        log.info("Sent message: " + response);
    }

    public void sendAllUser (List<User> users) throws InterruptedException, ExecutionException {

        for( User user : users){
            Message message = Message.builder()
                    .setToken(user.getFcmToken())
                    .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                            .setNotification(new WebpushNotification(user.getId().toString()+"에게 보내는 알림", "메세지내용입니다"))
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            log.info("Sent message: " + response);
        }
    }

    public void send(Message message) {
        FirebaseMessaging.getInstance().sendAsync(message);
    }

}
