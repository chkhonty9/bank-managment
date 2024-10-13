package org.cn.usersservice;

import org.cn.usersservice.configuration.RSAConfig;
import org.cn.usersservice.dto.CustomerDTO;
import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.service.CustomerService;
import org.cn.usersservice.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RSAConfig.class)
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService, RoleService roleService) {
        return args -> {
            roleService.save(new RoleDTO(null, "USER", "normal user"));
            roleService.save(new RoleDTO(null, "ADMIN", "ADMIN"));

            customerService.saveCustomer(new CustomerDTO(null, "Nouhaila Chkhonty", "nouhaila", "nouhaila", "nouhaila.com",null));
            customerService.saveCustomer(new CustomerDTO(null, "Admin", "admin", "admin", "admin.com",null));
            customerService.addRoleToCustomer(customerService.getCustomer(1L),roleService.findOne(1L));
            customerService.addRoleToCustomer(customerService.getCustomer(2L),roleService.findOne(2L));

        };
    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    };
}
