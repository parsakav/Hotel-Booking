package org.parsakav.hotelbooking.controller;


import jakarta.validation.Valid;
import org.parsakav.hotelbooking.dto.request.CustomerRequestDto;
import org.parsakav.hotelbooking.dto.response.CustomerResponseDto;
import org.parsakav.hotelbooking.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public CustomerResponseDto saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.save(customerRequestDto);
    }
    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAll();
    }  @GetMapping("findById/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable("id") long id) {
        return customerService.findById(id);
    } @GetMapping("findByEmail/{email}")
    public CustomerResponseDto getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.findByEmail(email);
    } @GetMapping("findByPhoneNumber/{number}")
    public CustomerResponseDto getCustomerByNumber(@PathVariable("number") String number) {
        return customerService.findByPhoneNumber(number);
    }

    @DeleteMapping
    public boolean deleteCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.delete(customerRequestDto);
    }
    @PatchMapping
    public boolean updateCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.update(customerRequestDto);
    }
}
