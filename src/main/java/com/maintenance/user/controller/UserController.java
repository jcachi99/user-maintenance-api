package com.maintenance.user.controller;

import com.maintenance.user.model.User;
import com.maintenance.user.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

    @GetMapping(path = "/paged")
    public ResponseEntity<?> listAllPaged(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "3") Integer size){
        Page<User> list = userService.getAllPaged(page,size);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable UUID userId, @RequestBody User user){
        return ResponseEntity.ok(userService.update(userId, user));
    }
}
