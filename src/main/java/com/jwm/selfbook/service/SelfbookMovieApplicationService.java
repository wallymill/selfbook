package com.jwm.selfbook.service;

import com.jwm.selfbook.entity.Movie;
import com.jwm.selfbook.entity.Users;
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

    public void findAndSuggestMoviePreferences(List<Integer> movieList) {
        final var movies = movieRepository.findAllById(movieList);
        logger.info("movies:  {}" , movies);
        final var users = usersRepository.findByMovieIdIn(movies);
        logger.info("users:  {}" , users);
        final var usersList = users.stream().map(Users::getUserId).toList();
        logger.info("usersList:  {}", usersList);
        final var filtered = usersRepository.findByUserIdInAndMovieIdNotIn(usersList, movies);
        logger.info("filtered:  {}", filtered);
        final var suggestionsList = filtered.stream().map(users1 -> users1.getMovieId().getMovieId()).toList();
        final var suggestions = movieRepository.findAllById(suggestionsList);
        logger.info("suggestions:  {}", suggestions);
        provideSuggestions(movies, suggestions);
    }

    private void provideSuggestions(List<Movie> movies, List<Movie> suggestions) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Since you enjoyed these title(s) \n   ");
        movies.forEach(movie -> sb.append(movie.getTitle()).append("\n   "));
        sb.append("\nYou may enjoy these suggestions \n   ");
        suggestions.forEach(movie -> sb.append(movie.getTitle()).append("\n   "));
        logger.info("sb:  {}", sb);
    }
}
