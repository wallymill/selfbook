package com.jwm.selfbook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMERS_MOVIES")
@IdClass(CustomersMoviesId.class)
public class CustomersMovies {

    @Id
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customers customers;

    @Id
    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movies movies;

    public Customers getCustomers() {
        return customers;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "CustomersMovies{" +
                "customers=" + customers +
                ", movies=" + movies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersMovies that = (CustomersMovies) o;
        return Objects.equals(customers, that.customers) && Objects.equals(movies, that.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers, movies);
    }
}
