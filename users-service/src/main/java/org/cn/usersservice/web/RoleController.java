package org.cn.usersservice.web;

import lombok.AllArgsConstructor;
import org.cn.usersservice.dto.RoleDTO;
import org.cn.usersservice.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        System.out.println(":::::::::::::::: get Roles rest controller");
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        System.out.println("::::::::::::::::::::: save role rest controller ");
        return ResponseEntity.ok(roleService.save(roleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        System.out.println("::::::::::::::::::::: update role rest controller ");
        if(roleService.findOne(id) == null)
            return ResponseEntity.notFound().build();
        roleDTO.setId(id);
        return ResponseEntity.ok(roleService.save(roleDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("::::::::::::::::::::: delete role rest controller ");
        if(roleService.findOne(id) == null)
            return ResponseEntity.notFound().build();
        roleService.delete(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name) {
        System.out.println("::::::::::::::::::::: get role by name rest controller ");
        RoleDTO roleDTO = roleService.findByName(name);
        if (roleDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roleDTO);
    }


}
