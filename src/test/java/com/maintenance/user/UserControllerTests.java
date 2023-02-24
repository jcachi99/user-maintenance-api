package com.maintenance.user;

import com.maintenance.user.controller.UserController;
import com.maintenance.user.domain.model.User;
import com.maintenance.user.service.impl.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private RequestAttributes attributes;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setup() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("jhony");
        user.setEmail("jcachi@outlook.cl");
        user.setPassword("Cachi.21");
        user.setActive(Boolean.TRUE);
        user.setLast_login(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @Test
    public void getAllUsers() {
        Mockito.when(userService.getAll()).thenReturn(Arrays.asList(user));
        Assert.assertNotNull(userController.listAll());
    }

}
