package com.jwm.selfbook.response;

import org.springframework.http.HttpStatus;

public class SelfbookMovieApplicationResponse {

  /**
   * The response message returned from {@link com.jwm.selfbook.service.SelfbookMovieApplicationService}.
   */
  private String response;

  /**
   * The HttpStatus returned from {@link com.jwm.selfbook.service.SelfbookMovieApplicationService}.
   */
  private HttpStatus httpStatus;

  /**
   * Gets the value of response.
   * @return response.
   */
  public String getResponse() {
    return response;
  }

  /**
   * Gets the value of httpStatus.
   * @return httpStatus.
   */
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  /**
   * Sets the value of httpStatus;
   * @param httpStatus
   */
  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  /**
   * Sets the value of response.
   * @param response
   */
  public void setResponse(String response) {
    this.response = response;
  }
}
