package com.jwm.selfbook.controller;

import com.jwm.selfbook.SelfbookMovieApplicationResponse;
import com.jwm.selfbook.service.SelfbookMovieApplicationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfbookMovieApplicationController {

  /**
   * The local singleton instance of {@link SelfbookMovieApplicationService}
   */
  private SelfbookMovieApplicationService selfbookMovieApplicationService;

  /**
   * The public constructor of {@link SelfbookMovieApplicationController} the spring container will
   * instantiate, injected with an instance of {@link SelfbookMovieApplicationService}.
   *
   * @param selfbookMovieApplicationService the instance of {@link SelfbookMovieApplicationService}.
   */
  public SelfbookMovieApplicationController(
      final SelfbookMovieApplicationService selfbookMovieApplicationService) {
    this.selfbookMovieApplicationService = selfbookMovieApplicationService;
  }

  /**
   * The GET request to offer movie preferences to the user.
   * @param movieIds the list of movie Ids.
   * @return the response message and HttpStatus.
   */
  @GetMapping("/movie-preferences")
  public ResponseEntity<String> getMoviePreferences(final List<Integer> movieIds) {
    SelfbookMovieApplicationResponse response = new SelfbookMovieApplicationResponse();
    response.setResponse("Hello World");
    response.setHttpStatus(HttpStatus.OK);
    return new ResponseEntity<>(response.getResponse(), response.getHttpStatus());
  }

}
