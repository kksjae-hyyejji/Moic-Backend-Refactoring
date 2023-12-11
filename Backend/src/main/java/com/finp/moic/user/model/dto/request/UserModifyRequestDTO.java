package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModifyRequestDTO {

    private String gender;
    private int yearOfBirth;

    public UserModifyRequestDTO(){

    }

    @Builder
    public UserModifyRequestDTO(String gender, int yearOfBirth){
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
}
