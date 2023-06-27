package com.jwm.selfbook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USERS_MOVIES")
@IdClass(UsersMoviesId.class)
public class UsersMovies {

    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private Users userId;

    @Id
    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movies movieId;

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Movies getMovieId() {
        return movieId;
    }

    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersMovies that = (UsersMovies) o;
        return Objects.equals(userId, that.userId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }

    @Override
    public String toString() {
        return "UsersMovies{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                '}';
    }
}
