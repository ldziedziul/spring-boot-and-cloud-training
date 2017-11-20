package com.example.users.controller;

import com.example.demo.common.Mapper;
import com.example.demo.common.dto.PageDto;
import com.example.demo.common.model.ResultPage;
import com.example.demo.common.web.UriBuilder;
import com.example.users.dto.UserDto;
import com.example.users.model.User;
import com.example.users.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RequestMapping("/users")
@RestController
public class UsersController {

    private UsersService usersService;
    private Mapper mapper;
    private UriBuilder uriBuilder = new UriBuilder();

    public UsersController(UsersService usersService, Mapper mapper) {
        this.usersService = usersService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Create new user")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@ApiParam(name = "user") @RequestBody UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        usersService.addUser(user);
        URI uri = uriBuilder.requestUriWithId(user.getId());
        return created(uri).build();
    }

    @ApiOperation(value = "Get users", response = PageDto.class)
    @RequestMapping(method = RequestMethod.GET)
    public PageDto<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "pageSize") int pageSize) {
        ResultPage<User> resultPage = usersService.getUsers(pageNumber, pageSize);
        List<UserDto> usersDtos = mapper.map(resultPage.getContent(), UserDto.class);
        return new PageDto<>(usersDtos, resultPage.getPageNumber(), resultPage.getTotalPages());
    }

    @ApiOperation(value = "Get active user", response = UserDto.class)
    @RequestMapping(value = "active", method = RequestMethod.GET)
    public UserDto getActiveUser(Principal principal) {
        UserDetails userDetails = usersService.loadUserByUsername(principal.getName());
        return mapper.map(userDetails, UserDto.class);
    }

}
