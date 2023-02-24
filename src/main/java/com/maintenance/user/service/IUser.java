package com.maintenance.user.service;

import com.maintenance.user.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IUser {

    List<User> getAll();
    Page<User> getAllPaged(Integer page, Integer size);
    User save(User user);
    User update(UUID userId, User user);
}
