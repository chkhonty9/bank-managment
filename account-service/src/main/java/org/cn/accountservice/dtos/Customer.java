package org.cn.accountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    private Long id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Collection<RoleDTO> roles = new ArrayList<>();
}
