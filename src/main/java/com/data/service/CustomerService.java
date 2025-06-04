package com.data.service;

import com.data.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void delete(int id);
    List<Customer> findByName(String name);
}
