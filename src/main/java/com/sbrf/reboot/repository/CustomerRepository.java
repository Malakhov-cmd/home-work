package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import lombok.NonNull;

import java.util.List;

public interface CustomerRepository {

    void createCustomerTable();

    boolean createCustomer(@NonNull String userName, String eMail);

    List<Customer> getAll();

    void deleteTableCustomer();

    void deleteCustomer(@NonNull Long id);
}
