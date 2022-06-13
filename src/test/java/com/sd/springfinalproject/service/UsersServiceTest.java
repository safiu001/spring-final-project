package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.UsersRepository;
import com.sd.springfinalproject.dto.UsersDto;
import com.sd.springfinalproject.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
class UsersServiceTest {

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Test
    void findById() {
//        Given
        Users user = new Users(1, "andy", "andy123", true, "Andy", "Bernard", null);
        given(usersRepository.findById(1)).willReturn(Optional.of(user));

//        When
        Users actualUser = usersService.findById(1);

//        Then
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void testFindById_idNotFound(){
        Optional<Users> user = Optional.empty();
        given(usersRepository.findById(1)).willReturn(user);
        Exception exception = assertThrows(RuntimeException.class, ()->usersService.findById(1));

        String expectedMessage = "user not found! 1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    void findAll() {
        List<Users> users = Arrays.asList(
                new Users(1, "andy", "andy123", true, "Andy", "Bernard", null),
                new Users(2, "oscar", "oscar123", true, "Oscar", "Martinez", null)
        );
        given(usersRepository.findAll()).willReturn(users);

        List<Users> actualUsers = usersService.findAll();

        assertThat(actualUsers).isEqualTo(users);
    }

    @Test
    void save() {
        Users user = new Users(1, "andy", "andy123", true, "Andy", "Bernard", null);
        usersService.save(user);

        then(usersRepository).should(times(1)).save(user);
    }

    @Test
    void testSave_whenCheckIsTrue() {
        Users user = new Users(1, "andy", "andy123", true, "Andy", "Bernard", null);
        usersService.save(user, true);

        then(usersRepository).should(times(1)).save(user);
    }

    @Test
    void deleteById() {
        usersService.deleteById(1);

        then(usersRepository).should(times(1)).deleteById(1);
    }

    @Test
    void findByUsername() {
        Users user = new Users(1, "andy", "andy123", true, "Andy", "Bernard", null);
        given(usersRepository.findByUsername("andy")).willReturn(user);

        Users actualUser = usersService.findByUsername("andy");
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void getUsersDto() {
        Users user = new Users(1, "andy", "andy123", true, "Andy", "Bernard", null);
        given(usersRepository.findByUsername("andy")).willReturn(user);

        UsersDto usersDto = new UsersDto("andy", "Andy", "Bernard", "Hello");
        UsersDto actualDto = usersService.getUsersDto("andy");

        assertThat(actualDto.getFullName()).isEqualTo(usersDto.getFullName());
        assertThat(actualDto.getFirstName()).isEqualTo(usersDto.getFirstName());
        assertThat(actualDto.getLastName()).isEqualTo(usersDto.getLastName());
        assertThat(actualDto.getUsername()).isEqualTo(usersDto.getUsername());
        assertThat(actualDto.getMessage()).isEqualTo(usersDto.getMessage());
    }
}