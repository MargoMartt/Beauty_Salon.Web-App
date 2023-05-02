package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.UsersEntity;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UsersService {
    public void saveUser(UsersEntity users);
    public UsersEntity getUser(int id);

    public void deleteUser (int id);
    public List<UsersEntity> getAllUser();
    public UsersEntity getByLogin(@NonNull String login);
}
