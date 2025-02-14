package org.parsakav.hotelbooking.service;

import org.parsakav.hotelbooking.dto.response.CustomerResponseDto;
import org.parsakav.hotelbooking.model.Customer;
import org.parsakav.hotelbooking.repository.CustomerRepository;
import org.parsakav.hotelbooking.dto.request.CustomerRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto findByEmail(String email) {
        Customer byEmail = customerRepository.findByEmail(email);
   CustomerResponseDto responseDto = new CustomerResponseDto();
   BeanUtils.copyProperties(byEmail, responseDto);
   return responseDto;
    }

    @Override
    public CustomerResponseDto findByPhoneNumber(String number) {
        Customer byPhoneNumber = customerRepository.findByPhoneNumber(number);
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(byPhoneNumber, customerResponseDto);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto findById(long id) {
        Customer byId = customerRepository.findById(id);
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(byId, customerResponseDto);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer);
        customer=customerRepository.save(customer);
        CustomerResponseDto responseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(customer, responseDto);
        return responseDto;
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerResponseDto customerResponseDto = new CustomerResponseDto();
                  BeanUtils.copyProperties(customer, customerResponseDto);
                  return customerResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public boolean update(CustomerRequestDto customer) {

        Customer customer1 = new Customer();
        BeanUtils.copyProperties(customer, customer1);
        int update = customerRepository.update(customer1);
      return update>0;
    }

    @Override
    public boolean delete(CustomerRequestDto customer) {
        Customer customer1 = new Customer();
        BeanUtils.copyProperties(customer, customer1);
        int delete = customerRepository.delete(customer1);
        return delete>0;
    }
}
