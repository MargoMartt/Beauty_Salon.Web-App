package com.example.beauty_salon.dao;


import com.example.beauty_salon.entity.UsersHasRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleHasUsersRepository extends JpaRepository<UsersHasRoleEntity, Integer> {
    public UsersHasRoleEntity findUsersHasRoleEntityByUsersIdUser(int id);
}