package com.sd.springfinalproject.service;

import com.sd.springfinalproject.entity.Authorities;

import java.util.List;

public interface AuthoritiesService {
    Authorities findById(int id);
    List<Authorities> findAll();
    void save(Authorities auth);
    void deleteById(int id);
}
