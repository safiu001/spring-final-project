package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dto.UsersDto;
import com.sd.springfinalproject.entity.Users;

import java.util.List;

public interface UsersService {

    Users findById(int id);
    List<Users> findAll();
    void save(Users user);
    void save(Users user, boolean check);
    void deleteById(int id);
    Users findByUsername(String username);
    UsersDto getUsersDto(String username);
}
