package org.cn.usersservice.service;



import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.dto.RoleDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO saveCustomer(CustomerDTO customer);
    public CustomerDTO findByUsername(String username);
    public CustomerDTO getCustomer(Long id);
    public List<CustomerDTO> getCustomers();
    public CustomerDTO addRoleToCustomer(CustomerDTO customer, RoleDTO role);
    public void deleteCustomer(Long id);

}
