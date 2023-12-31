package com.jwm.selfbook.service;

import com.jwm.selfbook.entity.CustomersMovies;
import com.jwm.selfbook.entity.Movies;
import com.jwm.selfbook.repository.CustomersMoviesRepository;
import com.jwm.selfbook.repository.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jwm.selfbook.enm.ResponseEnum.MOVIE_NOT_FOUND;
import static com.jwm.selfbook.enm.ResponseEnum.NO_SUGGESTIONS;

@Service
public class SelfbookMovieApplicationService {

    Logger logger = LoggerFactory.getLogger(SelfbookMovieApplicationService.class);

    /**
     * The local instance of {@link MoviesRepository}.
     */
    private final MoviesRepository moviesRepository;

    /**
     * The local instance of {@link CustomersMoviesRepository}.
     */
    private final CustomersMoviesRepository customersMoviesRepository;

    /**
     * The public constructor used by IOC to create the repositories.
     *
     * @param movieRepository
     * @param customersMoviesRepository
     */
    public SelfbookMovieApplicationService(final MoviesRepository movieRepository, final CustomersMoviesRepository customersMoviesRepository) {
        this.moviesRepository = movieRepository;
        this.customersMoviesRepository = customersMoviesRepository;
    }

    /**
     * This service accepts a list of Integers, which represent movie IDs.  From here, the service will utilize the appropriate JPARepository to retrieve Movies and UsersMovies data.
     *
     * @param movieList the Integer values that represent a {@link Movies} entry.
     * @return a ResponseEntity to the user.
     */
    public ResponseEntity<String> findAndSuggestMoviePreferences(List<Integer> movieList) {

        final var movies = moviesRepository.findAllById(movieList);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(MOVIE_NOT_FOUND.getResponse(), MOVIE_NOT_FOUND.getHttpStatus());
        }

        final var usersMovies = customersMoviesRepository.findByMoviesIn(movies);
        logger.debug("usersMovies:  {}", usersMovies);

        final var filtered = customersMoviesRepository.findByCustomersInAndMoviesNotIn(usersMovies.stream().map(CustomersMovies::getCustomers).toList(), movies);
        logger.debug("filtered:  {}", filtered);

        final var suggestions = moviesRepository.findAllById(filtered.stream().map(customersMovies -> customersMovies.getMovies().getMovieId()).toList());
        logger.debug("suggestions:  {}", suggestions);
        if (suggestions.isEmpty()) {
            return new ResponseEntity<>(NO_SUGGESTIONS.getResponse(), NO_SUGGESTIONS.getHttpStatus());
        }

        return new ResponseEntity<>(provideSuggestions(movies, suggestions), HttpStatus.OK);
    }

    private String provideSuggestions(List<Movies> movies, List<Movies> suggestions) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Since you enjoyed these title(s) \n   ");
        movies.forEach(movie -> sb.append(movie.getTitle()).append("\n   "));
        sb.append("\nYou may enjoy these suggestions \n   ");
        suggestions.forEach(movie -> sb.append(movie.getTitle()).append("\n   "));
        logger.debug("sb:  {}", sb);
        return sb.toString();
    }
}
