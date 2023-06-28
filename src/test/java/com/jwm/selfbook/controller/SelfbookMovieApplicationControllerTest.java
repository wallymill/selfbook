package com.jwm.selfbook.controller;

import com.jwm.selfbook.service.SelfbookMovieApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelfbookMovieApplicationControllerTest {

    @InjectMocks
    private SelfbookMovieApplicationController uut;

    @Mock
    private SelfbookMovieApplicationService selfbookMovieApplicationService;

    @DisplayName("Validate that a 200 http status is returned for a valid movie")
    @Test
    void test1() {
        //Arrange.
        final var expected = HttpStatus.OK;
        final var movieList = List.of(1,2);
        when(selfbookMovieApplicationService.findAndSuggestMoviePreferences(movieList)).thenReturn(new ResponseEntity<>(" ", expected));

        //Act.
        final var actual = uut.getMoviePreferences(movieList);

        //Assert.
        assertEquals(expected, actual.getStatusCode());


    }

}