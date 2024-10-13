package org.cn.usersservice.web;

import lombok.AllArgsConstructor;
import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.service.CustomerService;
import org.cn.usersservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        System.out.println(":::::::::::::::: getAllCustomers rest controller");
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println("::::::::::::::::::::: save customer rest controller ");
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        System.out.println("::::::::::::::::::::: update customer rest controller ");
        if(customerService.getCustomer(id) == null)
            return ResponseEntity.notFound().build();
        customerDTO.setId(id);
        return ResponseEntity.ok(customerService.saveCustomer(customerDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        System.out.println("::::::::::::::::::::: delete customer rest controller ");
        if(customerService.getCustomer(id) == null)
            return ResponseEntity.notFound().build();
        customerService.deleteCustomer(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        System.out.println("::::::::::::::::::::: get customer by id rest controller ");
        if(customerService.getCustomer(id) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerDTO> getCustomerByUsername(@PathVariable String username) {
        System.out.println("::::::::::::::::::::: get customer by username rest controller ");
        CustomerDTO customerDTO = customerService.findByUsername(username);
        if (customerDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/addRole")
    public ResponseEntity<CustomerDTO> addRole(@RequestParam String username, @RequestParam String role) {
        CustomerDTO customer = customerService.findByUsername(username);
        RoleDTO roleDTO = roleService.findByName(role);
        if(customer == null || roleDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customerService.addRoleToCustomer(customer, roleDTO));
    }

}
