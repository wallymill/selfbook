package com.jwm.selfbook.repository;

import com.jwm.selfbook.entity.Movies;
import com.jwm.selfbook.entity.Customers;
import com.jwm.selfbook.entity.CustomersMovies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomersMoviesRepository extends JpaRepository<CustomersMovies, Integer> {

    public List<CustomersMovies> findByMoviesIn(List<Movies> movies);

    public List<CustomersMovies> findByCustomersInAndMoviesNotIn(List<Customers> customers, List<Movies> movies);

}
