package org.cn.accountservice.controller;

import lombok.AllArgsConstructor;

import org.cn.accountservice.clients.CustomerRestClient;
import org.cn.accountservice.dtos.AccountDTO;
import org.cn.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/accounts")
@AllArgsConstructor
public class AccountController {
    AccountService accountService;
    CustomerRestClient customerRestClient;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getComptes() {
        System.out.println(":::::::: controller compte getting all :::::::");
        List<AccountDTO> accounts = accountService.findAll();
        accounts.forEach( a->{
            a.setCustomer(customerRestClient.getCustomer(a.getCustomerId()));
        });
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createCompte(@RequestBody AccountDTO compteDTO) {
        System.out.println(":::::::: controller compte creating :::::::");
        AccountDTO account = accountService.save(compteDTO);
        account.setCustomer(customerRestClient.getCustomer(account.getCustomerId()));
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateCompte(@RequestBody AccountDTO accountDTO, @PathVariable Long id) {
        System.out.println(":::::::: controller compte updating :::::::");
        accountDTO.setId(id);
        AccountDTO account = accountService.save(accountDTO);
        account.setCustomer(customerRestClient.getCustomer(account.getCustomerId()));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getCompteById(@PathVariable Long id) {
        System.out.println(":::::::: controller compte getting by id :::::::");
        AccountDTO account = accountService.findById(id);
        account.setCustomer(customerRestClient.getCustomer(account.getCustomerId()));
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public void deleteCompteById(@PathVariable Long id) {
        System.out.println(":::::::: controller deleting :::::::");
        accountService.deleteById(id);

    }

    @GetMapping("/credit")
    public ResponseEntity<AccountDTO> credit(Long id, double amount) {
        System.out.println(":::::::: controller getting credit :::::::");
        AccountDTO account = accountService.crediter(id,amount);
        account.setCustomer(customerRestClient.getCustomer(account.getCustomerId()));
        return ResponseEntity.ok(account);

    }
    @GetMapping("/debit")
    public ResponseEntity<AccountDTO> debit(Long id, double amount) {
        System.out.println(":::::::: controller getting credit :::::::");
        AccountDTO account = accountService.debiter(id,amount);
        account.setCustomer(customerRestClient.getCustomer(account.getCustomerId()));
        return ResponseEntity.ok(account);

    }


}
