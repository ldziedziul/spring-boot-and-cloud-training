package com.example.users.service;

import com.example.demo.common.model.ResultPage;
import com.example.users.model.User;
import com.example.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Value("${defaultDepartmentId}")
    private Long defaultDepartmentId;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addUser(User user) {
        configureDepartment(user);
        usersRepository.saveAndFlush(user);
    }

    private void configureDepartment(User user) {
        if (user.getDepartmentId() == null) {
            user.setDepartmentId(defaultDepartmentId);
        }
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }
}
