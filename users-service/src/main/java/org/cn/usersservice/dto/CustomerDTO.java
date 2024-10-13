package org.cn.usersservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class CustomerDTO {

    private Long id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private Collection<RoleDTO> roles = new ArrayList<>();
}
