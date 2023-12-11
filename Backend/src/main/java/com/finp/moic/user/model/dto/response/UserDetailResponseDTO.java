package com.finp.moic.user.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDetailResponseDTO {
    private String name;
    private String email;
    private String gender;
    private int yearOfBirth;


    public UserDetailResponseDTO(){

    }

    @Builder
    public UserDetailResponseDTO(String name, String email, String gender, int yearOfBirth){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
}
