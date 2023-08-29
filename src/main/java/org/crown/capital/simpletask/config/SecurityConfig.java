package org.crown.capital.simpletask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder auth) throws Exception {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;

        // Configure the global settings
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/"),
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/user/signin"),
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/user/signup"),
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/WEB-INF/jsp/signin.jsp"),
                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/WEB-INF/jsp/signup.jsp")
                        ).permitAll()
//                        .requestMatchers("/user/signin").permitAll()
//                        .requestMatchers("/user/signup").permitAll()
                        .anyRequest().hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/user/signin")
                                .defaultSuccessUrl("/list_task")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }


//    private final UserServiceImpl userDetailsService;
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfig(UserServiceImpl userDetailsService, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder auth) throws Exception {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//
//        // Configure the global settings
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
//    @Bean
//    public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
//
//        http.sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                                .invalidSessionUrl("/user/signin")
//                )
//                .authorizeRequests((requests) -> requests
//                        .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/"),
//                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/user/signin"),
//                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/user/signup"),
//                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/WEB-INF/jsp/signin.jsp"),
//                                new MvcRequestMatcher(new HandlerMappingIntrospector(), "/WEB-INF/jsp/signup.jsp")
//                        )
//                        .permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/user/signin")
//                        .successForwardUrl("/list_tasks")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll);
//
//        return http.build();
//    }

}
