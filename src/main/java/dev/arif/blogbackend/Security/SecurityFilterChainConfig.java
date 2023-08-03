package dev.arif.blogbackend.Security;

import dev.arif.blogbackend.Jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static dev.arif.blogbackend.User.Role.ADMIN;
import static dev.arif.blogbackend.User.Role.USER;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers(
                                    "/api/v1/auth/**",
                                    "api/v1/register/**"
                            )
                            .permitAll()
                            .requestMatchers(
                                    "/api/v1/subjects/**",
                                    "/api/v1/blogs/**",
                                    "/api/v1/comments/**",
                                    "/api/v1/replies/**"
                            )
                            .hasAnyRole(USER.name(), ADMIN.name())
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout->{
                    logout.logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(logoutHandler)
                            .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));
                })
                .build();
    }
}
