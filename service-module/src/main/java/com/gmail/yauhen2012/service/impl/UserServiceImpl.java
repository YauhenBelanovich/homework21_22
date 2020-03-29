package com.gmail.yauhen2012.service.impl;

import javax.transaction.Transactional;

import com.gmail.yauhen2012.repository.UserRepository;
import com.gmail.yauhen2012.repository.model.User;
import com.gmail.yauhen2012.service.UserService;
import com.gmail.yauhen2012.service.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    @Transactional
    public UserDTO loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        return convertDatabaseObjectToDTO(user);
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        User user = convertUserDTOToDatabaseUser(userDTO);
        userRepository.add(user);
    }

    private UserDTO convertDatabaseObjectToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User convertUserDTOToDatabaseUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        user.setRole(userDTO.getRole());
        return user;
    }

}
