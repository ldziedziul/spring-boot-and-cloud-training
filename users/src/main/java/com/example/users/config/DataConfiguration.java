package com.example.users.config;

import com.example.users.model.User;
import com.example.users.service.UsersService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DataConfiguration {

    private final UsersService usersService;

    public DataConfiguration(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostConstruct
    public void init() {
        addUsersIfMissing();
    }

    private void addUsersIfMissing() {
        if (usersService.getUsers(0, 1).getContent().isEmpty()) {
            User user = new User();
            user.setFirstName("Jan");
            user.setLastName("Nowak");
            usersService.addUser(user);
        }
    }
}
