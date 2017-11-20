package com.example.users.service;

import com.example.demo.common.model.ResultPage;
import com.example.users.model.Authority;
import com.example.users.model.Role;
import com.example.users.model.User;
import com.example.users.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        configureAuthority(user);
        encodePassword(user);
        user.setActive(true);
        usersRepository.saveAndFlush(user);
    }

    private void configureAuthority(User user) {
        Authority authority = new Authority(Role.USER.name());
        user.addAuthority(authority);
    }

    private void encodePassword(User user) {
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usersRepository.getByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", login)));

    }

}
