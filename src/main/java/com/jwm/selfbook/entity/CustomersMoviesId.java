package com.jwm.selfbook.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * This is the {@link javax.persistence.IdClass} used for the CUSTOMERS_MOVIES table to create a composite primary key of USERS and MOVIES.
 */
public class CustomersMoviesId implements Serializable {

    @Serial
    private static final long serialVersionUID = -6485609871329579174L;

    private Customers customers;
    private Movies movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersMoviesId that = (CustomersMoviesId) o;
        return Objects.equals(customers, that.customers) && Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers, movies);
    }
}
