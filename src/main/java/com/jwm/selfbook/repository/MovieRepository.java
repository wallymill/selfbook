package com.jwm.selfbook.repository;

import com.jwm.selfbook.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
