package com.sd.springfinalproject.dao;

import com.sd.springfinalproject.entity.MovieList;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomQueryRepositoryImpl implements CustomQueryRepository{
    private EntityManager entityManager;

    @Override
    public List<MovieList> findAllByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        return null;
    }
}
