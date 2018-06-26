package org.hejin.app.security;

import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;

public class LogoutSuccessEvent extends AbstractAuthenticationEvent {

    public LogoutSuccessEvent(Authentication authentication) {
        super(authentication);
    }
}
