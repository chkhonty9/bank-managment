package org.cn.usersservice.service.impl;

import lombok.AllArgsConstructor;
import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.mapper.CustomerMapper;
import org.cn.usersservice.mapper.RoleMapper;
import org.cn.usersservice.repository.CustomerRepository;
import org.cn.usersservice.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        System.out.println(":::::::::::::::: saving customer : " + customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerMapper.toCustomerDTO(customerRepository.save(customerMapper.toCustomer(customer)));
    }

    @Override
    public CustomerDTO findByUsername(String username) {
        System.out.println(":::::::::::::::: find By Username : " + username);
        return customerMapper.toCustomerDTO(customerRepository.findByUsername(username));
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        System.out.println("::::::::::::::::::: get customer by id : " + id);
        return customerMapper.toCustomerDTO(customerRepository.findById(id).orElse(null));
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        System.out.println("::::::::::::::::::: get customers ");
        return customerRepository.findAll().stream().map(c->customerMapper.toCustomerDTO(c)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO addRoleToCustomer(CustomerDTO customer, RoleDTO role) {
        System.out.println("::::::::::::::::::::::: adding role : " + role + " to customer : " + customer);
        customer.getRoles().add(role);
        return customerMapper.toCustomerDTO(customerRepository.save(customerMapper.toCustomer(customer)));

    }

    @Override
    public void deleteCustomer(Long id) {
        System.out.println("::::::::::::::::::::: deleting customer : " + id);
        customerRepository.deleteById(id);
    }
}
