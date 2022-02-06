package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
        customerRepository.createCustomerTable();
    }

    @Test
    void getAll() {
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");

        List<Customer> all = customerRepository.getAll();

        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomer() {

        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");

        assertTrue(mariaCreated);
    }

    @Test
    void deleteCustomerById() {
        customerRepository.createCustomer("Gansales", "dangerios@yastreb.ru");
        customerRepository.createCustomer("Pico", "laud@ya.ru");

        customerRepository.deleteCustomer(2L);

        List<Customer> customers = customerRepository.getAll();

        assertTrue(customers.stream().noneMatch(item -> item.getName().equals("Gansales")));
    }

    @AfterAll
    public static void after(){
        customerRepository.deleteTableCustomer();
    }
}