package com.auth.service.Impl;

import com.auth.model.ERole;
import com.auth.model.Role;
import com.auth.repository.RoleRepository;
import com.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            role.setId(id);
            return roleRepository.save(role);
        } else {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public boolean deleteRole(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            roleRepository.delete(roleOptional.get());
            return true;
        }
        return false;
    }
}
