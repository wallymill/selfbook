package com.jwm.selfbook.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.UUID;

/**
 * The database representation of the USERS table, consisisting of a user ID and a movie.
 */
@Entity
@Table(name = "USERS")
public class Users {

    /**
     * Primary key for the USERS entity, representing a single user and a single movie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * User ID
     */

    @Column(name = "user_id")
    private Integer userId;

    /**
     * Foreign key to the MOVIE table, representing a MOVIE entity.
     */
    @OneToOne
    @JoinColumn
    private Movie movie;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
