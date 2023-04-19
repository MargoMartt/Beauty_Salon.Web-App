package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.entity.RoleEntity;

import java.util.List;

public interface RoleService {

    public void saveRole(RoleEntity role);
    public RoleEntity getRole(int id);

    public void deleteRole (int id);
    public List<RoleEntity> getAllRoles();
}
