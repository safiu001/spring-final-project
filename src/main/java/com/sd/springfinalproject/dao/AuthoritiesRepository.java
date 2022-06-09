package com.sd.springfinalproject.dao;

import com.sd.springfinalproject.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}
