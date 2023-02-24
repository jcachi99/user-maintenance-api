package com.maintenance.user.service.impl;

import com.maintenance.user.exception.ResourceNotFoundException;
import com.maintenance.user.repository.UserRepository;
import com.maintenance.user.model.User;
import com.maintenance.user.service.IUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.maintenance.user.util.Constants.EMAIL_DUPLICATED;

@Service
@Slf4j
public class UserService implements IUser {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllPaged(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return userRepository.findAll(paging);
    }

    @Override
    public User save(User user) {
        try {
            user.setActive(Boolean.TRUE);
            user.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            user.setLast_login(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            user.setToken(UUID.randomUUID().toString());
            return userRepository.save(user);
        } catch (Exception e){
            throw new ExpressionException(EMAIL_DUPLICATED,e.getMessage());
        }
    }

    @Override
    public User update(UUID userId, User user) {
        User existingClient = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario"));
        existingClient.setName(user.getName());
        existingClient.getPhones().removeAll(existingClient.getPhones());
        existingClient.getPhones().addAll(user.getPhones());
        existingClient.setEmail(user.getEmail());
        existingClient.setPassword(user.getPassword());
        existingClient.setActive(user.getActive());
        existingClient.setToken(user.getToken());
        existingClient.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        existingClient.setLast_login(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return userRepository.save(existingClient);
    }

}
