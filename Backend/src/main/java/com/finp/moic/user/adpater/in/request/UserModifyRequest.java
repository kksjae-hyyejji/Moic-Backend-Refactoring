package com.finp.moic.user.adpater.in.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModifyRequest {

    private String gender;
    private int yearOfBirth;

    public UserModifyRequest(){

    }

    @Builder
    public UserModifyRequest(String gender, int yearOfBirth){
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
}
