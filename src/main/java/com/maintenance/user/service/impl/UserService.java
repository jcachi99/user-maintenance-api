package com.maintenance.user.service.impl;

import com.maintenance.user.repository.UserRepository;
import com.maintenance.user.domain.model.User;
import com.maintenance.user.service.IUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUser {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
