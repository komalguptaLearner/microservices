package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.datamodel.Customer;

@Repository
public interface CustomerSORepository extends JpaRepository<Customer, Long> {
}
