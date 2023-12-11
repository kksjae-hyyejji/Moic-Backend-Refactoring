package com.finp.moic.voc.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VOCRequestDTO {

    @NotNull
    @NotBlank
    private String content;

    public VOCRequestDTO(){

    }
    public VOCRequestDTO(@NotNull String content) {
        this.content = content;
    }
}
