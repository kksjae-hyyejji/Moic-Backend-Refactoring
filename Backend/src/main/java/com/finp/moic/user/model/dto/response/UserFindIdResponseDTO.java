package com.finp.moic.user.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFindIdResponseDTO {
    private String id;

    public UserFindIdResponseDTO(){

    }

    @Builder
    public UserFindIdResponseDTO(String id){
        this.id = id;
    }
}
