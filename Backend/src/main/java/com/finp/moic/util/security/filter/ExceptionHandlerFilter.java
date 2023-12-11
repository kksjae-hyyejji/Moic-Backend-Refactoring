package com.finp.moic.util.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.BusinessExceptionEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper;

    public ExceptionHandlerFilter(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(e.getError().getStatus().value());
            BusinessExceptionEntity error = new BusinessExceptionEntity(e.getError().getErrorCode(),e.getError().getErrorMessage());
            response.getWriter().write(mapper.writeValueAsString(error));
        }
    }

}
