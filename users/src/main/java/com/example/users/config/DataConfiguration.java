package com.example.users.config;

import com.example.users.model.Authority;
import com.example.users.model.Role;
import com.example.users.model.User;
import com.example.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;

@Configuration
public class DataConfiguration {

    @Autowired
    private UsersService usersService;

    @PostConstruct
    public void init() {
        addUserIfMissing("admin", "admin", Role.ADMIN);
        addUserIfMissing("user", "user", Role.USER);
    }

    private void addUserIfMissing(String login, String password, Role role) {
        try {
            usersService.loadUserByUsername(login);
        } catch (UsernameNotFoundException ex) {
            User user = new User(login, password);
            user.addAuthority(new Authority(role.name()));
            usersService.addUser(user);
        }
    }
}
