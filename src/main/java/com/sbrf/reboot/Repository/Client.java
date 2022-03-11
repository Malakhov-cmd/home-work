package com.sbrf.reboot.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    private Long id;
    private String username;
    private String password;

    private Double amountValue;
}
