package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.AuthoritiesRepository;
import com.sd.springfinalproject.entity.Authorities;
import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
class AuthoritiesServiceTest {
    @MockBean
    AuthoritiesRepository authoritiesRepository;

    @Autowired
    AuthoritiesService authoritiesService;

    @Test
    void findById() {
//        Given
        Users user = new Users();
        user.setId(1);
        user.setUsername("ryan");
        user.setPassword("ryan123");
        user.setEnabled(true);
        user.setFirstName("Ryan");
        user.setLastName("Howard");
        user.setMovies(null);

        Authorities authority = new Authorities();
        authority.setAuthId(1);
        authority.setUser(user);
        authority.setUsername("ryan");
        authority.setAuthority("ROLE_USER");

        given(authoritiesRepository.findById(1)).willReturn(Optional.of(authority));

//        When
        Authorities auth = authoritiesService.findById(1);

//        Then
        assertThat(auth.getAuthId()).isEqualTo(authority.getAuthId());
        assertThat(auth.getAuthority()).isEqualTo(authority.getAuthority());
        assertThat(auth.getUsername()).isEqualTo(authority.getUsername());
        assertThat(auth.getUser()).isEqualTo(authority.getUser());
    }

    @Test
    void testFindById_idNotFound(){
        Optional<Authorities> authority = Optional.empty();
        given(authoritiesRepository.findById(1)).willReturn(authority);
        Exception exception = assertThrows(RuntimeException.class, ()->authoritiesService.findById(1));

        String expectedMessage = "error while loading the data from id 1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    void findAll() {
        List<Authorities> authorities = Arrays.asList(new Authorities(1, "ROLE_USER", null, "ryan"),
                new Authorities(2, "ROLE_USER", null, "andy"));
        given(authoritiesRepository.findAll()).willReturn(authorities);

        List<Authorities> actualValue = authoritiesService.findAll();

        assertThat(actualValue).isEqualTo(authorities);
    }

    @Test
    void save() {
        Authorities authority = new Authorities(2, "ROLE_USER", null, "andy");

        authoritiesService.save(authority);

        then(authoritiesRepository).should(times(1)).save(authority);
    }

    @Test
    void deleteById() {
        Authorities authority = new Authorities(2, "ROLE_USER", null, "andy");

        authoritiesService.deleteById(2);

        then(authoritiesRepository).should(times(1)).deleteById(2);
    }


}