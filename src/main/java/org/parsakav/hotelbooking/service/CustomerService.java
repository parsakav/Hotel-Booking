package org.parsakav.hotelbooking.service;

import org.parsakav.hotelbooking.dto.request.CustomerRequestDto;
import org.parsakav.hotelbooking.dto.response.CustomerResponseDto;
import org.parsakav.hotelbooking.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto findByEmail(String email);
    CustomerResponseDto findByPhoneNumber(String number);

    CustomerResponseDto findById(long id);
    CustomerResponseDto save(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> getAll();
    boolean update(CustomerRequestDto customer);
    boolean delete(CustomerRequestDto customer);
}
