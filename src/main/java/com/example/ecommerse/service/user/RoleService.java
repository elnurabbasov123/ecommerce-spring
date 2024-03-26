package com.example.ecommerse.service.user;

import com.example.ecommerse.model.entity.Role;
import com.example.ecommerse.model.enums.type.RoleType;

public interface RoleService {
    Role getRoleByName(RoleType roleType);
}
