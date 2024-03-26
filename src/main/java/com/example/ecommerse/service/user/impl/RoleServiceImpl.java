package com.example.ecommerse.service.user.impl;

import com.example.ecommerse.model.entity.Role;
import com.example.ecommerse.model.enums.type.RoleType;
import com.example.ecommerse.repository.RoleRepository;
import com.example.ecommerse.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getRoleByName(RoleType roleType) {
        return roleRepository.getRoleByName(roleType).orElseThrow();
    }
}
