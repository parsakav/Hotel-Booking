package org.parsakav.hotelbooking;
import org.parsakav.hotelbooking.model.Customer;
import org.parsakav.hotelbooking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class HotelBookingApplication {
 public static void main(String[] args) {
        /*Customer customer = new Customer();
        customer.setAddress("x");
        customer.setName("p");
        customer.setEmail("Z");
        customer.setPhoneNumber("917400");*/
        ConfigurableApplicationContext run = SpringApplication.run(HotelBookingApplication.class, args);
/*
        run.getBean(CustomerRepository.class).save(customer);
*/
    }
}