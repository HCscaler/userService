package com.auth.service;

import com.auth.model.ERole;
import com.auth.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Long id, Role role);
    boolean deleteRole(Long id);
}
