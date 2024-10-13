package org.cn.accountservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cn.accountservice.dtos.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createAt;
    private double balance;
    private String currency;
    private Long customerId;
    @Transient
    private Customer customer;
}
