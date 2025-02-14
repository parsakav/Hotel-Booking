package org.parsakav.hotelbooking.repository;


import org.parsakav.hotelbooking.model.Customer;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends Crud<Customer> {


    Customer findByEmail(String email);
    Customer findByPhoneNumber(String number);

}
