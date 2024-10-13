package org.cn.accountservice.service;


import org.cn.accountservice.dtos.AccountDTO;

import java.util.List;

public interface AccountService {
    public AccountDTO save(AccountDTO  compteDTO);
    public AccountDTO  findById(Long id);
    public List<AccountDTO> findAll();
    public void deleteById(Long id);
    public AccountDTO debiter(Long id, double amount);
    public AccountDTO crediter(Long id, double amount);
}
