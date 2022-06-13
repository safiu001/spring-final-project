package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.AuthoritiesRepository;
import com.sd.springfinalproject.entity.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public Authorities findById(int id) {
        Optional<Authorities> optional = authoritiesRepository.findById(id);
        Authorities auth = null;

        if(optional.isPresent()){
            auth = optional.get();
        }else{throw new IdNotFoundException("error while loading the data from id "+id);}
        return auth;
    }

    @Override
    public List<Authorities> findAll() {
        return authoritiesRepository.findAll();
    }

    @Override
    public void save(Authorities auth) {
        authoritiesRepository.save(auth);
    }

    @Override
    public void deleteById(int id) {
        authoritiesRepository.deleteById(id);
    }
}
