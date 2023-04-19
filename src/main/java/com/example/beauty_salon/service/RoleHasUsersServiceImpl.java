package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.RoleHasUsersRepository;
import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.entity.UsersHasRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleHasUsersServiceImpl implements RoleHasUsersService {

    @Autowired
    RoleHasUsersRepository repository;

    @Override
    public void saveRoleHasUsers(UsersHasRoleEntity role) {
        repository.save(role);
    }

    @Override
    public UsersHasRoleEntity getRoleHasUsers(int id) {
        UsersHasRoleEntity role = null;
        Optional<UsersHasRoleEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Override
    public void deleteRoleHasUsers(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<UsersHasRoleEntity> getAllRoleHasUsers() {
        return repository.findAll();
    }
}
