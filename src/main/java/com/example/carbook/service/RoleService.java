package com.example.carbook.service;

import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;

public interface RoleService {

    UserRoleEntity findByRole(UserRoleEnum role);
}
