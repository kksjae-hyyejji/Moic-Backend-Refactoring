package com.finp.moic.util.security.config;

import com.finp.moic.util.security.filter.ExceptionHandlerFilter;
import com.finp.moic.util.security.filter.UnAuthenticationEntryPoint;
import com.finp.moic.util.security.filter.JwtAuthenticationFilter;
import com.finp.moic.util.security.handler.CustomAccessDeniedHandler;
import com.finp.moic.util.security.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.finp.moic.util.security.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.finp.moic.util.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final UnAuthenticationEntryPoint unAuthenticationEntryPoint;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccessDeniedHandler customAccessDeniedHandler,
                          UnAuthenticationEntryPoint unAuthenticationEntryPoint, HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository,
                          OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
                          ExceptionHandlerFilter exceptionHandlerFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.unAuthenticationEntryPoint = unAuthenticationEntryPoint;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .cors(cors ->
                        cors
                                .configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/user/login","/user/regist","/auth/**","/user/check/**",
                                        "/user/lookup/id", "/user/temp/password", "/user/reset/password",
                                        "/user/verify/password").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(handler ->
                        handler
                            .authenticationEntryPoint(unAuthenticationEntryPoint)
                            .accessDeniedHandler(customAccessDeniedHandler))
                .oauth2Login(oauth ->
                        oauth
                                .authorizationEndpoint(endpoint ->
                                        endpoint
                                                .baseUri("/oauth2/authorization")
                                                .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository))
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .failureHandler(oAuth2AuthenticationFailureHandler))
                //로그아웃 했을 때 이동할 페이지
                .logout((logout) -> logout.logoutSuccessUrl("/"))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter,JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","https://moic.site"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
