package com.jwm.selfbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMERS")
public class Customers {

    @Id
    @Column(name = "customer_id")
    private Integer customer;

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(this.customer, customers.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customer=" + customer +
                '}';
    }
}
