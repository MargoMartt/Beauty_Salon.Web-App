package com.example.beauty_salon.security;

import com.example.beauty_salon.dao.RoleHasUsersRepository;
import com.example.beauty_salon.dao.RoleRepository;
import com.example.beauty_salon.dao.UsersRepository;
import com.example.beauty_salon.entity.RoleEntity;
import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.service.RoleHasUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RoleHasUsersRepository roleHasUsersService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = userRepository.getByLogin(username);
        System.out.println("loadUsersByUsername");
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("success");
        System.out.println(user);
        return new User(user.getLogin(), user.getPassword(), getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(UsersEntity user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        int userId = user.getIdUser();
        System.out.println("vov");
        int roleId = roleHasUsersService.findUsersHasRoleEntityByUsersIdUser(userId).getRoleIdRole();
        RoleEntity role = roleRepository.getByIdRole(roleId);

        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        return authorities;
    }
}
