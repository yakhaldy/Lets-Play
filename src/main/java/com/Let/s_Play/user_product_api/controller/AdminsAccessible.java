package com.Let.s_Play.user_product_api.controller;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Let.s_Play.user_product_api.dto.UserResponse;
import com.Let.s_Play.user_product_api.service.UsersService;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminsAccessible {
    private final UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>>  getAllUsers() {
        List<UserResponse> users = usersService.getAllUsers(); 
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id){
        usersService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}