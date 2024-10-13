package org.cn.accountservice.service.imp;

import lombok.AllArgsConstructor;
import org.cn.accountservice.dtos.AccountDTO;
import org.cn.accountservice.entities.Account;
import org.cn.accountservice.mappers.AccountMapper;
import org.cn.accountservice.repository.AccountRepository;
import org.cn.accountservice.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository compteRepository;
    private final AccountMapper compteMapper;

    @Override
    public AccountDTO save(AccountDTO  compteDTO) {
        System.out.println(":::::: saving compte ::::::::");
        compteDTO.setCreateAt(LocalDate.now());
        return compteMapper.fromCompte(compteRepository.save(compteMapper.toCompte(compteDTO)));
    }

    @Override
    public AccountDTO  findById(Long id) {
        System.out.println(":::::: finding compte ::::::::");
        return compteMapper.fromCompte(compteRepository.findById(id).orElse(null));
    }

    @Override
    public List<AccountDTO > findAll() {
        System.out.println(":::::: getting all comptes ::::::::");
        return compteRepository.findAll().stream().map(compteMapper::fromCompte).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        System.out.println(":::::: deleting compte ::::::::");
        compteRepository.deleteById(id);

    }

    @Override
    public AccountDTO debiter(Long id, double amount) {
        System.out.println(":::::: debiting account ::::::::");
        Account compte = compteRepository.findById(id).orElse(null);
        if(compte == null || compte.getBalance() < amount) {
            return null;
        }
        compte.setBalance(compte.getBalance() - amount);
        return this.save(compteMapper.fromCompte(compte));
    }

    @Override
    public AccountDTO crediter(Long id, double amount) {
        System.out.println("::::::::: crediter account");
        Account compte = compteRepository.findById(id).orElse(null);
        if(compte == null)
            return null;
        compte.setBalance(compte.getBalance() + amount);
        return this.save(compteMapper.fromCompte(compte));
    }
}
