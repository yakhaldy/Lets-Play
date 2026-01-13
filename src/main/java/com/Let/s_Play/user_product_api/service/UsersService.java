package com.Let.s_Play.user_product_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Let.s_Play.user_product_api.dto.UserResponse;
import com.Let.s_Play.user_product_api.exception.ResourceNotFoundException;
import com.Let.s_Play.user_product_api.repository.UserRepository;


@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .toList();
        return users;
    }


    public void deleteUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " does not exist");
        }
        if (userRepository.findById(id).get().getRole().equals("ROLE_ADMIN")) {
            throw new IllegalArgumentException("Cannot delete an admin user");
        }
        userRepository.deleteById(id);
    }

}
