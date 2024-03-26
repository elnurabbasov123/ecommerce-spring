package com.example.ecommerse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.example.ecommerse.model.constant.EndPoints.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(HttpMethod.POST, AUTH_REGISTRATION).anonymous()
                                .requestMatchers(HttpMethod.POST, AUTH_CONFIRM).permitAll()
                                .requestMatchers(HttpMethod.DELETE, USERS).hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/users").hasRole("USER")
                                .requestMatchers("/orders/**").authenticated()
                                .requestMatchers("/orders/cart-add/**").hasRole("USER")
                                .requestMatchers("/cards").authenticated()
                                .requestMatchers("/orders/incr-quantity/{productId}").authenticated()
                                .requestMatchers("/customers/registration").hasRole("USER")
                                .requestMatchers("/sellers/registration").hasRole("CUSTOMER")
                                .requestMatchers("/admins/user/**").hasRole("ADMIN")
                                .requestMatchers("/products/languages").authenticated()
                                .requestMatchers("/products/**").permitAll()
                                .requestMatchers("/files/**").permitAll()
                                .requestMatchers("/admins/seller-confirmation/**").hasRole("ADMIN")
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOrigin("/swagger-ui/**");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

}
