package com.jwm.selfbook.service;

import com.jwm.selfbook.entity.Customers;
import com.jwm.selfbook.entity.CustomersMovies;
import com.jwm.selfbook.entity.Movies;
import com.jwm.selfbook.repository.CustomersMoviesRepository;
import com.jwm.selfbook.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.jwm.selfbook.enm.ResponseEnum.MOVIE_NOT_FOUND;
import static com.jwm.selfbook.enm.ResponseEnum.NO_SUGGESTIONS;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelfbookMovieApplicationServiceTest {

    @InjectMocks
    private SelfbookMovieApplicationService uut;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private CustomersMoviesRepository customersMoviesRepository;

    private List<Integer> movieIdList;
    private List<Movies> moviesList;
    private List<CustomersMovies> usersCustomersMoviesList;
    private List<CustomersMovies> filteredCustomersMoviesList;
    private List<Customers> customersList;

    @BeforeEach
    void setUp() {
        int movie = 1;
        int customerId = 2;

        movieIdList = List.of(movie);

        Movies movies = new Movies();
        movies.setMovieId(movie);
        movies.setTitle("title");
        moviesList = List.of(movies);

        Customers customers = new Customers();
        customers.setCustomer(customerId);
        customersList = List.of(customers);

        CustomersMovies customersMovies = new CustomersMovies();
        customersMovies.setMovies(movies);
        customersMovies.setCustomers(customers);
        usersCustomersMoviesList = List.of(customersMovies);
        filteredCustomersMoviesList = List.of(customersMovies);
    }

    @DisplayName("Invalid Customer movie selections return MOVIE_NOT_FOUND")
    @Test
    void test1() {
        //Arrange.
        final var expected = new ResponseEntity<>(MOVIE_NOT_FOUND.getResponse(), MOVIE_NOT_FOUND.getHttpStatus());
        when(moviesRepository.findAllById(movieIdList)).thenReturn(Collections.emptyList());

        //Act
        final var actual = uut.findAndSuggestMoviePreferences(movieIdList);

        //Assert.
        assertSoftly(softAssertions -> {
            assertEquals(expected.getBody(), actual.getBody());
            assertEquals(expected.getStatusCode(), actual.getStatusCode());
        });
    }

    @DisplayName("No suggestions available returns NO_SUGGESTIONS")
    @Test
    void test2() {
        //Arrange.
        final var expected = new ResponseEntity<>(NO_SUGGESTIONS.getResponse(), NO_SUGGESTIONS.getHttpStatus());
        when(moviesRepository.findAllById(movieIdList)).thenReturn(moviesList, Collections.emptyList());
        when(customersMoviesRepository.findByMoviesIn(moviesList)).thenReturn(usersCustomersMoviesList);
        when(customersMoviesRepository.findByCustomersInAndMoviesNotIn(customersList, moviesList)).thenReturn(filteredCustomersMoviesList);

        //Act.
        final var actual = uut.findAndSuggestMoviePreferences(movieIdList);

        //Act and Assert.
        assertSoftly(softAssertions -> {
            assertEquals(expected.getBody(), actual.getBody());
            assertEquals(expected.getStatusCode(), actual.getStatusCode());
        });
    }

    @DisplayName("Suggestions return with a 200 http status and list of suggestions")
    @Test
    void test3() {
        //Arrange.
        when(moviesRepository.findAllById(movieIdList)).thenReturn(moviesList, moviesList);
        when(customersMoviesRepository.findByMoviesIn(moviesList)).thenReturn(usersCustomersMoviesList);
        when(customersMoviesRepository.findByCustomersInAndMoviesNotIn(customersList, moviesList)).thenReturn(filteredCustomersMoviesList);

        //Act.
        final var actual = uut.findAndSuggestMoviePreferences(movieIdList);

        //Assert.
        assertSoftly(softAssertions -> {
            assertEquals(HttpStatus.OK, actual.getStatusCode());
            assertTrue(Objects.requireNonNull(actual.getBody()).startsWith("\nSince you enjoyed these title(s) \n   "));
            assertTrue(actual.getBody().contains("\nYou may enjoy these suggestions \n   "));
        });
    }

}