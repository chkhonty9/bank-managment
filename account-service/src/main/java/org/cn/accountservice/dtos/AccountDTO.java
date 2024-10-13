package org.cn.accountservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDTO {
    private Long id;
    private LocalDate createAt;
    private double balance;
    private String currency;
    private Long customerId;
    private Customer customer;
}
