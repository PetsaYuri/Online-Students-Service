package com.online.students.service.config.SpringSecurity;

import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    public CustomAuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserEntity user = userService.getOneByEmail(authentication.getName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (user == null) {
            throw new BadCredentialsException("Email doesn't exist");
        }

        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Password doesn't match");
        }

        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
