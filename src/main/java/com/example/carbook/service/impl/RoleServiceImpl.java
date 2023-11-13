package com.example.carbook.service.impl;

import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.RoleRepository;
import com.example.carbook.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRoleEntity findByRole(UserRoleEnum role) {
        return roleRepository.findByRole(role);
    }
}
