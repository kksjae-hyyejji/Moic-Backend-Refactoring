package com.finp.moic.util.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.util.exception.BusinessExceptionEntity;
import com.finp.moic.util.exception.ExceptionEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper mapper;

    public CustomAccessDeniedHandler(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ExceptionEnum.HANDLE_ACCESS_DENIED.getStatus().value());
        BusinessExceptionEntity error = new BusinessExceptionEntity(ExceptionEnum.HANDLE_ACCESS_DENIED.getErrorCode(),ExceptionEnum.HANDLE_ACCESS_DENIED.getErrorMessage());
        response.getWriter().write(mapper.writeValueAsString(error));
    }
}
