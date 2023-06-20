package com.jwm.selfbook.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "movie_id")
    @ManyToOne()
    private Movie movieId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(userId, users.userId) && Objects.equals(movieId, users.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, movieId);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userId=" + userId +
                ", movie_id=" + movieId +
                '}';
    }
}
