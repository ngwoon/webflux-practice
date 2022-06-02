package com.example.webfluxpractice.service;

import com.example.webfluxpractice.persistence.Employee;
import com.example.webfluxpractice.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void create(Employee e) {
        employeeRepository.save(e).subscribe();
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> update(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return employeeRepository.deleteById(id);
    }
}
