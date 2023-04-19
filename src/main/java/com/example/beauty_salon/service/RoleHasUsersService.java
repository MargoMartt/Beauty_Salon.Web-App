package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.UsersHasRoleEntity;

import java.util.List;

public interface RoleHasUsersService {
    public void saveRoleHasUsers(UsersHasRoleEntity role);
    public UsersHasRoleEntity getRoleHasUsers(int id);

    public void deleteRoleHasUsers (int id);
    public List<UsersHasRoleEntity> getAllRoleHasUsers();
}

