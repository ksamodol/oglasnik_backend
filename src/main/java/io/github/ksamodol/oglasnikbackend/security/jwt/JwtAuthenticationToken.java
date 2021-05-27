package io.github.ksamodol.oglasnikbackend.security.jwt;

import io.github.ksamodol.oglasnikbackend.security.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private User user;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, User user) {
        super(authorities);
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (Collection<GrantedAuthority>) user.getAuthorities();
    }
    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
