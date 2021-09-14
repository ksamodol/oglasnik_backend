package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.repository.UserRepository;
import io.github.ksamodol.oglasnikbackend.security.User;
import io.github.ksamodol.oglasnikbackend.security.UserCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User '" + username + "' not found")
                );
    }

    public User save(UserCommand userCommand) throws IllegalArgumentException{
        if(userRepository.existsByUsername(userCommand.getUsername())){
            throw new IllegalArgumentException("Username already in use!");
        }
        if(userRepository.existsByEmail(userCommand.getEmail())){
            throw new IllegalArgumentException("Email already in use!");
        }
        return userRepository.save(mapCommandToUser(userCommand));
    }

    private User mapCommandToUser(UserCommand userCommand){
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setPhoneNumber(userCommand.getPhoneNumber());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setTimestampCreated(Instant.now());
        user.setListings(Collections.emptyList()); //TODO: null?
        return user;
    }
}
