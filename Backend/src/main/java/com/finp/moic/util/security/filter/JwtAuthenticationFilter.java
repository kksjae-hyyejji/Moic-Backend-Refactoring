package com.finp.moic.util.security.filter;

import com.finp.moic.util.security.dto.UserAuthentication;
import com.finp.moic.util.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //헤더에서 AUTHORIZATION에 담긴 토큰 가져오기
        String token = parseBearerToken(request);
        //만약 토큰이 없다면 걍 넘김
        if(token==null){
            filterChain.doFilter(request,response);
            return;
        }

        try{
            jwtService.validateToken(token);
        }catch(ExpiredJwtException expiredJwtException){
            filterChain.doFilter(request,response);
            return;
        }catch (JwtException | IllegalArgumentException | NullPointerException e){
            filterChain.doFilter(request,response);
            return;
        }

        //user id 가져오기
        UserAuthentication userAuthentication = parseUserSpecification(token);

        //AuthenticationToken 제작
        List<String> roles = Arrays.asList("USER");
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userAuthentication,null,authorities);
        //detail 설정
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);

    }

    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        return authorization;
    }

    private UserAuthentication parseUserSpecification(String token) {

        String id = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 1)
                .map(jwtService::getSubject)
                .orElse("null");
        return new UserAuthentication(id);
    }

}
