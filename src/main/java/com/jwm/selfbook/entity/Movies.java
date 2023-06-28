package com.jwm.selfbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * This Hibernate Entity represents the MOVIES table, which is generated from the data.sql file (via the {@link com.jwm.selfbook.GenerateDataSql} process).
 */
@Entity
@Table(name = "MOVIES")
public class Movies {

    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "title")
    private String title;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movie = (Movies) o;
        return Objects.equals(movieId, movie.movieId) && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, title);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", title='" + title + '\'' +
                '}';
    }
}
