package com.bookordering.common.domain.entity;

import com.bookordering.common.domain.entity.base.AggregatedEntity;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Customer extends AggregatedEntity<Long> {
    private String name;

    public Customer() {}

    public Customer(String name,Long id) {
        super.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ",id='" + super.getId()+ '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }
}
