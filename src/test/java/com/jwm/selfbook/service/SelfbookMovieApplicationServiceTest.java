package com.jwm.selfbook.service;

import com.jwm.selfbook.repository.CustomersMoviesRepository;
import com.jwm.selfbook.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jwm.selfbook.enm.ResponseEnum.MOVIE_NOT_FOUND;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelfbookMovieApplicationServiceTest {

    @InjectMocks
    private SelfbookMovieApplicationService uut;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private CustomersMoviesRepository customersMoviesRepository;

    private List<Integer> movieList;

    @BeforeEach
    void setUp() {
        movieList = new ArrayList<>();
        movieList.add(1);
    }

    @DisplayName("Invalid Customer movie selections return MOVIE_NOT_FOUND")
    @Test
    void test1() {
        //Arrange.
        final var expected = new ResponseEntity<>(MOVIE_NOT_FOUND.getResponse(), MOVIE_NOT_FOUND.getHttpStatus());
        when(moviesRepository.findAllById(movieList)).thenReturn(Collections.emptyList());

        //Act
        final var actual = uut.findAndSuggestMoviePreferences(movieList);

        //Act and Assert.
        assertSoftly(softAssertions -> {
            assertEquals(expected.getBody(), actual.getBody());
            assertEquals(expected.getStatusCode(), actual.getStatusCode());
        });
    }

}