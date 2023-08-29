package org.crown.capital.simpletask.service.impl;

import org.crown.capital.simpletask.dto.UserDTO;
import org.crown.capital.simpletask.model.Role;
import org.crown.capital.simpletask.model.User;
import org.crown.capital.simpletask.repository.RoleRepository;
import org.crown.capital.simpletask.repository.UserRepository;
import org.crown.capital.simpletask.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Lazy
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDTO userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User checkExistingUsername(String username) {

        return userRepository.findByUserName(username)
                .orElse(null);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}

