package com.example.webfluxpractice.web;

import com.example.webfluxpractice.persistence.Employee;
import com.example.webfluxpractice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployController {

    private final EmployeeService employeeService;

    public EmployController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = { "/create", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
        employeeService.create(e);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(e, status);
    }

    @GetMapping(value = "/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @PutMapping(value = "/update")
    public Mono<Employee> update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        employeeService.delete(id).subscribe();
    }
}
