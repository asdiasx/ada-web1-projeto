package com.adriano.adaweb1projeto.repositories;

import com.adriano.adaweb1projeto.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByRegistrationNumber(String registrationNumber);

    Optional<List<Employee>> findByNameContainingIgnoreCase(String partialName);
}
