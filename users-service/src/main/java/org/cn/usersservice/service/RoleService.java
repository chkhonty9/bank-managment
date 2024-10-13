package org.cn.usersservice.service;

import org.cn.usersservice.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO role);
    RoleDTO findOne(Long id);
    RoleDTO findByName(String name);
    List<RoleDTO> findAll();
    void delete(Long id);

}
