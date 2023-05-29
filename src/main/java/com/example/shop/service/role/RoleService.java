package com.example.shop.service.role;

import com.example.shop.entities.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    void createRole(Role role);
    void deleteRole(Role role);
}
