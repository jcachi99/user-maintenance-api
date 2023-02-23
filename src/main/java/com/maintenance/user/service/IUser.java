package com.maintenance.user.service;

import com.maintenance.user.domain.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUser {

    List<User> getAll();
    Page<User> getAllPaged(Integer page, Integer size);
    User save(User user);
}
