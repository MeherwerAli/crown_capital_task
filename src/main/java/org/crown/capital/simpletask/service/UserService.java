package org.crown.capital.simpletask.service;

import org.crown.capital.simpletask.dto.UserDTO;
import org.crown.capital.simpletask.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User saveUser(UserDTO registrationDto);
    User findByUsername(String userName) throws UsernameNotFoundException;

    User checkExistingUsername(String username);

//    List<UserDTO> findAllUsers();

}

