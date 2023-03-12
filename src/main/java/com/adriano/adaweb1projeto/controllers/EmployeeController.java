package com.adriano.adaweb1projeto.controllers;

import com.adriano.adaweb1projeto.model.Employee;
import com.adriano.adaweb1projeto.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/employees")
    public String addUser(@RequestParam("registrationNumber") String registrationNumber,
                          @RequestParam("name") String name,
                          @RequestParam("document") String document,
                          @RequestParam("dependentsQty") int dependentsQty,
                          @RequestParam("active") boolean active) {
        Employee employee = new Employee();
        employee.setRegistrationNumber(registrationNumber);
        employee.setName(name);
        employee.setDocument(document);
        employee.setDependentsQty(dependentsQty);
        employee.setActive(active);
        employeeService.save(employee);

        return "redirect:employees";
    }

    @GetMapping("/add-employee")
    public String createEmployees() {
        return "add-employees";
    }

    @PostMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String detailsOfEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "details-employee";
    }

    @PostMapping("/search")
    public String searchFor(@RequestParam("name") String name, Model model) {
        List<Employee> employees = employeeService.findByName(name);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
