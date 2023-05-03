package com.example.beauty_salon.security;

import com.example.beauty_salon.dao.RoleHasUsersRepository;
import com.example.beauty_salon.dao.RoleRepository;
import com.example.beauty_salon.dao.UsersRepository;
import com.example.beauty_salon.entity.Role;
import com.example.beauty_salon.entity.RoleEntity;
import com.example.beauty_salon.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleHasUsersRepository roleHasUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.getByLogin(username);
        RoleEntity role = roleRepository.getByIdRole(roleHasUsersRepository.findUsersHasRoleEntityByUsersIdUser(user.getIdUser()).getRoleIdRole());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        System.out.println("loadUserByUsername");
        System.out.println(username);
        System.out.println(user);
        System.out.println(authorities);

        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
