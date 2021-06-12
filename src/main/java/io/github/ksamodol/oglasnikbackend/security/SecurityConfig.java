package io.github.ksamodol.oglasnikbackend.security;

import io.github.ksamodol.oglasnikbackend.security.jwt.JwtConfig;
import io.github.ksamodol.oglasnikbackend.security.jwt.JwtTokenVerifier;
import io.github.ksamodol.oglasnikbackend.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private UserDetailsService userDetailsService;
    public SecurityConfig(JwtConfig jwtConfig, SecretKey secretKey, UserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//TODO: remove
                .cors().and() //TODO: Remove
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, userDetailsService), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers("/login", "/register", "/location/*", "/location/**").permitAll() //TODO: fix!
                .antMatchers("/", "/**").permitAll();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){ return NoOpPasswordEncoder.getInstance();} //TODO: change
}
