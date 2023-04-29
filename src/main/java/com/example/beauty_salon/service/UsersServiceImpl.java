package com.example.beauty_salon.service;


import com.example.beauty_salon.dao.ServiceRepository;
import com.example.beauty_salon.dao.UsersRepository;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository repository;

    @Override
    public void saveUser(UsersEntity user) {
        repository.save(user);

    }

    @Override
    public UsersEntity getUser(int id) {
        UsersEntity user = null;
        Optional<UsersEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);

    }

    @Override
    public List<UsersEntity> getAllUser() {
        return repository.findAll();
    }

    public UsersEntity getByLogin(@NonNull String login){
        return repository.getByLogin(login);
    }
}
