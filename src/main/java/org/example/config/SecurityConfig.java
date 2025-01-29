package org.example.config;

import org.example.security.JwtAuthenticationFilter;
import org.example.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.Filter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService userDetailsService) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/*.html"),
                                AntPathRequestMatcher.antMatcher("/*.js"),
                                AntPathRequestMatcher.antMatcher("/*.css"),
                                AntPathRequestMatcher.antMatcher("/img/**"),
                                AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/users/register"),
                                AntPathRequestMatcher.antMatcher("/users/login")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/admins/register"),
                                AntPathRequestMatcher.antMatcher("/shelters/register"),
                                AntPathRequestMatcher.antMatcher("/adopters/register")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/auth/**"),
                                AntPathRequestMatcher.antMatcher("/login"),
                                AntPathRequestMatcher.antMatcher("/register"),
                                AntPathRequestMatcher.antMatcher("/error")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/admins/**")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/shelters/**")).hasRole("SHELTER")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/adopters/**")).hasRole("ADOPTER")
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/pets/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Unauthorized - Please login");
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:63342")); // הפורט של ה-IDE שלך
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}