package com.jwm.selfbook.entity;

import jakarta.persistence.*;

/**
 * The database table that represents the Movie table, consisting of it's unique ID and its Title.
 */
@Entity
@Table(name = "MOVIE")
public class Movie {

    /**
     * The primary key for the MOVIE entity.
     */
    @Id
    private Integer id;

    /**
     * The title of the movie.
     */
    @Column
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
