package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.UserDTO;
import com.ecommerce.backend.dto.UserLoginDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    String loginUser(UserLoginDTO userLoginDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
}
