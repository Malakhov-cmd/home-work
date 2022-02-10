package com.sbrf.reboot.repository.statements;

import lombok.Getter;

public enum Statements {
    createStatement("CREATE TABLE CUSTOMER (\r\n" + "  id  int8(256) primary key,\r\n" +
            "  name varchar(128),\r\n" + "  email varchar(256)" + " );"),
    insertStatement("INSERT INTO CUSTOMER \r\n" +
                             "  (id, name, email) VALUES  (?, ?, ?);"),
    selectAllStatement("SELECT * FROM CUSTOMER;"),
    deleteCustomerById("DELETE FROM CUSTOMER WHERE id = (?)"),
    deleteCustomersTable("DROP TABLE IF EXISTS CUSTOMER;");

    @Getter
    private final String state;

    Statements(String state) {
        this.state = state;
    }
}
