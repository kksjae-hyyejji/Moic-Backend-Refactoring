package com.finp.moic.util.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.util.exception.BusinessExceptionEntity;
import com.finp.moic.util.exception.ExceptionEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Autowired
    public UnAuthenticationEntryPoint(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ExceptionEnum.UNAUTHENTICATED_MEMBER.getStatus().value());
        BusinessExceptionEntity error = new BusinessExceptionEntity(ExceptionEnum.UNAUTHENTICATED_MEMBER.getErrorCode(),ExceptionEnum.UNAUTHENTICATED_MEMBER.getErrorMessage());
        response.getWriter().write(mapper.writeValueAsString(error));
    }
}
