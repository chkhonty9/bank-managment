package org.cn.usersservice.service.impl;

import lombok.AllArgsConstructor;
import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.mapper.RoleMapper;
import org.cn.usersservice.repository.RoleRepository;
import org.cn.usersservice.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Override
    public RoleDTO save(RoleDTO role) {
        System.out.println("::::::::::::: saving role");
        return roleMapper.fromRole(roleRepository.save(roleMapper.toRole(role)));
    }

    @Override
    public RoleDTO findOne(Long id) {
        System.out.println("::::::::::::::::: finding role by id : " + id);
        return roleMapper.fromRole(roleRepository.findById(id).orElse(null));
    }

    @Override
    public RoleDTO findByName(String name) {
        System.out.println("::::::::::::::::::: finding role by name : " + name);
        return roleMapper.fromRole(roleRepository.findByName(name));
    }

    @Override
    public List<RoleDTO> findAll() {
        System.out.println("::::::::::::::::::: finding all roles");
        return roleRepository.findAll().stream().map(r->roleMapper.fromRole(r)).toList();
    }

    @Override
    public void delete(Long id) {
        System.out.println("::::::::::::::::::: deleting role by id : " + id);
        roleRepository.deleteById(id);
    }
}
