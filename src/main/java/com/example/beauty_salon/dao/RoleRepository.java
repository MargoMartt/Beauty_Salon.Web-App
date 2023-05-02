package com.example.beauty_salon.dao;
import com.example.beauty_salon.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    public RoleEntity getByIdRole(int id);
}