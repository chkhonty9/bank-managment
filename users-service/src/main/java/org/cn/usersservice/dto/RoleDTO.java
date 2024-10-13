package org.cn.usersservice.dto;


import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
}
