package com.data.repository;

import com.data.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
    List<Customer> findByName(String name);
}
