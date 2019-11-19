package com.training.salon.service;


import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private static String WRONG_INPUT = "Wrong input!!";


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
         return userRepository.findByEmail(email);
    }

    public void updateUserByAdmin(User user, Map<String, String> form) throws DataIntegrityViolationException {
        //TODO rewrite for 1 key
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name)
                .collect(Collectors.toSet());
        form.keySet().stream()
                .filter(roles::contains)
                .forEach((key) -> user.setRole(Role.valueOf(key)));
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            log.info("Can`t change role for user " + user.getEmail());
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateProfile(User user, String firstName, String lastName, String password) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        }catch(Exception e){
            log.info("Can`t save user");
            log.info("{}", user);
        }
    }

    public void saveNewUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            log.info("User " + user.getEmail() + " was successfully registered.");
        } catch (Exception ex) {
            log.info("User is already exists");
        }
    }


}
