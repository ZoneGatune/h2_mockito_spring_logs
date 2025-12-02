package com.example.springbootswaggerh2.repository;

import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
