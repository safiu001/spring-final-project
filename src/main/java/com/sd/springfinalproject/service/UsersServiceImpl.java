package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.UsersRepository;
import com.sd.springfinalproject.dto.UsersDto;
import com.sd.springfinalproject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users findById(int id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);

        Users user = null;

        if(optionalUsers.isPresent()){
            user = optionalUsers.get();
        }else{
            throw new RuntimeException("user not found! "+id);
        }
        return user;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public void save(Users user) {
        usersRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public UsersDto getUsersDto(String username) {
        Users user = findByUsername(username);
        return new UsersDto(user.getUsername(), user.getFirstName(), user.getLastName(), "Hello");
    }

    @Override
    public void save(Users user, boolean check) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(check);
        usersRepository.save(user);
    }
}
