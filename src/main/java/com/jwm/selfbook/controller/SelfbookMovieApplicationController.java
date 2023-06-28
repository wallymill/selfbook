package com.jwm.selfbook.controller;

import com.jwm.selfbook.service.SelfbookMovieApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelfbookMovieApplicationController {

  Logger logger = LoggerFactory.getLogger(SelfbookMovieApplicationController.class);

  /**
   * The local singleton instance of {@link SelfbookMovieApplicationService}
   */
  private final SelfbookMovieApplicationService selfbookMovieApplicationService;

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
  public ResponseEntity<String> getMoviePreferences(@RequestParam final List<Integer> movieIds) {
    logger.debug("in getMoviePreferences:  {}", movieIds);
    return selfbookMovieApplicationService.findAndSuggestMoviePreferences(movieIds);

  }

}
