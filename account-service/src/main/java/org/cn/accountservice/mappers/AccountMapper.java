package org.cn.accountservice.mappers;

import lombok.AllArgsConstructor;
import org.cn.accountservice.dtos.AccountDTO;
import org.cn.accountservice.dtos.Customer;
import org.cn.accountservice.entities.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountMapper {

    public AccountDTO fromCompte(Account compte) {
       AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(compte, accountDTO);
        if(compte.getCustomer() != null) {
            accountDTO.setCustomer(compte.getCustomer());
        }
        return accountDTO;
    }

    public Account toCompte(AccountDTO accountDTO) {
        Account compte = new Account();
        BeanUtils.copyProperties(accountDTO, compte);
        if(accountDTO.getCustomer() != null) {
            compte.setCustomer(accountDTO.getCustomer());
        }
        return compte;
    }
}
