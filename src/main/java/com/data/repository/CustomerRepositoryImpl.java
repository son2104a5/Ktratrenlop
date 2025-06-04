package com.data.repository;

import com.data.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        String jpql = "SELECT c FROM Customer c";
        return em.createQuery(jpql, Customer.class).getResultList();
    }

    @Override
    public Customer findById(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == 0) {
            em.persist(customer);
        } else {
            em.merge(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        Customer existingCustomer = em.find(Customer.class, customer.getId());
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setImageUrl(customer.getImageUrl());
            existingCustomer.setFileImage(existingCustomer.getFileImage());
            em.merge(existingCustomer);
        }
    }

    @Override
    public void delete(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.remove(customer);
        }
    }

    @Override
    public List<Customer> findByName(String name) {
        String jpql = "SELECT c FROM Customer c WHERE c.firstName LIKE :name OR c.lastName LIKE :name";
        return em.createQuery(jpql, Customer.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
