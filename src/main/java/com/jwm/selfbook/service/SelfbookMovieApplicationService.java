package com.jwm.selfbook.service;

import com.jwm.selfbook.repository.MovieRepository;
import com.jwm.selfbook.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfbookMovieApplicationService {

    Logger logger = LoggerFactory.getLogger(SelfbookMovieApplicationService.class);

    private final MovieRepository movieRepository;
    private final UsersRepository usersRepository;

    public SelfbookMovieApplicationService(final MovieRepository movieRepository, final UsersRepository usersRepository) {
        this.movieRepository = movieRepository;
        this.usersRepository = usersRepository;
    }

    public void findAndSuggestMoviePreferences (List<Integer> movieIds) {
        final var movies = movieRepository.findAllById(movieIds);
        logger.info("movies:  {}", movies);
        final var users = usersRepository.findMovieIdAndUserIdByMoviesIn(movies);
        logger.info("users:   {}", users);
//        final var suggestions = usersRepository.findDistinctByUserIdInAndMovieIdNotIn(users, movies);
//        logger.info("suggestions:  {}", suggestions);
    }

}
