package org.cn.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.cn.accountservice.dtos.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@FeignClient(name = "USERS-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/v1/api/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomer")
    Customer getCustomer(@PathVariable Long id);

    @GetMapping("/v1/api/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomers")
    List<Customer> getCustomers();

    default Customer getCustomer(Long id, Exception ex){
        System.out.println("Fallback triggered for getCustomer: " + ex.getMessage());
        System.out.println("Fallback triggered for getCustomer: " + Arrays.toString(ex.getStackTrace()));
        Customer customer = new Customer();
        customer.setId(id);
        customer.setEmail("not available");
        customer.setUsername("not available");
        customer.setPassword("not available");
        customer.setFullName("not available");
        customer.setRoles(null);
        return customer;
    }

    default List<Customer> getCustomers(Exception ex){
        return List.of();
    }



}
