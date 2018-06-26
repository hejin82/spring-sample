package org.hejin.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsernamePasswordAuthenticationFilter extends org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class);

    private int usernameMinLength = 10;
    private int usernameMaxLength = 10;
    private int passwordMinLength = 8;
    private int passwordMaxLength = 20;

    public int getUsernameMinLength() {
        return usernameMinLength;
    }

    public void setUsernameMinLength(int usernameMinLength) {
        this.usernameMinLength = usernameMinLength;
    }

    public int getUsernameMaxLength() {
        return usernameMaxLength;
    }

    public void setUsernameMaxLength(int usernameMaxLength) {
        this.usernameMaxLength = usernameMaxLength;
    }

    public int getPasswordMinLength() {
        return passwordMinLength;
    }

    public void setPasswordMinLength(int passwordMinLength) {
        this.passwordMinLength = passwordMinLength;
    }

    public int getPasswordMaxLength() {
        return passwordMaxLength;
    }

    public void setPasswordMaxLength(int passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null || username.length() < this.usernameMinLength || username.length() > this.usernameMaxLength) {
            throw new UsernameNotFoundException("username error");
        }

        if (password == null || password.length() < this.passwordMinLength || password.length() > this.usernameMaxLength) {
            throw new UsernameNotFoundException("password error");
        }
        return super.attemptAuthentication(request, response);
    }
}
