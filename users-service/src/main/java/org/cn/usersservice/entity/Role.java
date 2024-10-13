package org.cn.usersservice.entity;



import lombok.*;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
@Entity
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
}
