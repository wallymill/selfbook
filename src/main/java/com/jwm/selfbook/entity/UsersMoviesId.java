package com.jwm.selfbook.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UsersMoviesId implements Serializable {

    @Serial
    private static final long serialVersionUID = -6485609871329579174L;

    private Users userId;
    private Movies movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersMoviesId that = (UsersMoviesId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}
