package com.jwm.selfbook.repository;

import com.jwm.selfbook.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

}
