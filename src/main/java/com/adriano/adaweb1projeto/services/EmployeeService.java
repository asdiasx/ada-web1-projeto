package com.adriano.adaweb1projeto.services;

import com.adriano.adaweb1projeto.model.Employee;
import com.adriano.adaweb1projeto.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByRegistrationNumber(String registrationNumber) {
        return employeeRepository.findByRegistrationNumber(registrationNumber).orElse(null);
    }

    public List<Employee> findByName(String partialName) {
        return employeeRepository.findByNameContainingIgnoreCase(partialName).orElse(null);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
