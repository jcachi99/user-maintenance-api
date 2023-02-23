package com.maintenance.user.controller;

import com.maintenance.user.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user/v1")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(userService.getAll());
    }
}
