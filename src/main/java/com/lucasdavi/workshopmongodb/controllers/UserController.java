package com.lucasdavi.workshopmongodb.controllers;

import com.lucasdavi.workshopmongodb.dtos.UserDTO;
import com.lucasdavi.workshopmongodb.models.User;
import com.lucasdavi.workshopmongodb.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user.getName(), user.getEmail())).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") String id) {
        Optional<User> user = Optional.ofNullable(userService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

}
