package com.example.pwatestbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String fcmToken;


    @Builder
    public User(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void updateFcm(String fcmToken){
        this.fcmToken = fcmToken;
    }

    public static User of(String fcmToken){
        return User.builder().fcmToken(fcmToken).build();
    }

    public void clearFcm(){
        this.fcmToken = "cleared";
    }
}