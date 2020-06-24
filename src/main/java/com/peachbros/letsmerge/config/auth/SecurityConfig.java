package com.peachbros.letsmerge.config.auth;

import com.peachbros.letsmerge.user.model.domain.Role;
import com.peachbros.letsmerge.user.model.repository.CustomOAuth2UserService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
