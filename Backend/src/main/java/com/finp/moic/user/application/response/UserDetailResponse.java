package com.finp.moic.user.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailResponse {
    private String name;
    private String email;
    private String gender;
    private int yearOfBirth;


    public UserDetailResponse(){

    }

    @Builder
    public UserDetailResponse(String name, String email, String gender, int yearOfBirth){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
}
