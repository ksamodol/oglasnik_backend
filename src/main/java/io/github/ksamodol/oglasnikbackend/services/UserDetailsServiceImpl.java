package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.exception.UserAlreadyExistsException;
import io.github.ksamodol.oglasnikbackend.repository.UserRepository;
import io.github.ksamodol.oglasnikbackend.security.User;
import io.github.ksamodol.oglasnikbackend.security.UserCommand;
import io.github.ksamodol.oglasnikbackend.security.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User '" + username + "' not found")
                );
    }

    public UserDTO save(UserCommand userCommand) throws IllegalArgumentException, UserAlreadyExistsException {
        if(userAlreadyExists(userCommand)){
            throw new UserAlreadyExistsException();
        }
        User user = userRepository.save(mapCommandToUser(userCommand));
        return mapUserToDTO(user);
    }

    private User mapCommandToUser(UserCommand userCommand){
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setTimestampCreated(Instant.now());
        user.setListings(List.of()); //TODO: null?

        return user;
    }

    private UserDTO mapUserToDTO(User user){
        return new UserDTO(user.getUsername(), user.getPassword());
    }

    private boolean userAlreadyExists(UserCommand userCommand){
        return userRepository.existsByUsername(userCommand.getUsername()) || userRepository.existsByEmail(userCommand.getEmail());
    }

}
