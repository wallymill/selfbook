package com.jwm.selfbook.repository;

import com.jwm.selfbook.entity.Movie;
import com.jwm.selfbook.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    public List<Users> findByMovieIdIn(List<Movie> movies);

    public List<Users> findByUserIdIn(List<Integer> users);

    public List<Users> findByUserIdInAndMovieIdNotIn(List<Integer> users, List<Movie> movies);

}
