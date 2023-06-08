package com.jwm.selfbook.entity;

import jakarta.persistence.*;

/**
 * The database representation of the USERS table, consisisting of a user ID and a movie.
 */
@Entity
@Table(name = "USERS")
public class Users {

    /**
     * Primary key for the USERS entity, representing a single user.
     */
    @Id
    private Integer id;

    /**
     * Foreign key to the MOVIE table, representing a MOVIE entity.
     */
    @OneToOne
    @JoinColumn
    private Movie movie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
