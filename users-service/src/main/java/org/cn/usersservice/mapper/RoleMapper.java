package org.cn.usersservice.mapper;

import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public RoleDTO fromRole(Role role) {
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }

    public Role toRole(RoleDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        return role;
    }
}
