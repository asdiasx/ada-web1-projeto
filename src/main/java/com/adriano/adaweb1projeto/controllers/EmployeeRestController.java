package com.adriano.adaweb1projeto.controllers;

import com.adriano.adaweb1projeto.model.Employee;
import com.adriano.adaweb1projeto.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        boolean duplicated_reg_num = employeeService.findAll().stream()
                .anyMatch(e -> e.getRegistrationNumber().equals(employee.getRegistrationNumber()));
        boolean duplicated_doc = employeeService.findAll().stream()
                .anyMatch(e -> e.getDocument().equals(employee.getDocument()));

        if (duplicated_reg_num) {
            return ResponseEntity.badRequest().body("{\"ERROR:\":\"Matrícula já cadastrada!\"}");
        }
        if (duplicated_doc) {
            return ResponseEntity.badRequest().body("{\"ERROR:\":\"Documento já cadastrado!\"}");
        }

        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/registration-number")
    public Employee findByRegistrationNumber(@RequestParam String registrationNumber) {
        return employeeService.findByRegistrationNumber(registrationNumber);
    }

    @GetMapping("/name")
    public List<Employee> searchName(@RequestParam String name) {
        return employeeService.findByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee employee) {

        if (employee.getName() == null || employee.getDocument() == null || employee.getRegistrationNumber() == null) {
            System.out.println("Nome, Documento e Matrícula não podem ser nulos!");
            return ResponseEntity.badRequest().body("Nome, Documento e Matrícula não podem ser nulos!");
        }

        employee.setId(id);

        try {
            employeeService.save(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
