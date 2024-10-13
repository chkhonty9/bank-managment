package org.cn.usersservice.mapper;

import lombok.AllArgsConstructor;
import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerMapper {

    private RoleMapper roleMapper;

    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        if(customerDTO.getRoles() != null) {
            customer.setRoles(customerDTO.getRoles().stream().map(r->roleMapper.toRole(r)).collect(Collectors.toList()));
        }
        return customer;
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if(customer.getRoles() != null) {
            customerDTO.setRoles(customer.getRoles().stream().map(r->roleMapper.fromRole(r)).collect(Collectors.toList()));
        }
        return customerDTO;
    }

}
