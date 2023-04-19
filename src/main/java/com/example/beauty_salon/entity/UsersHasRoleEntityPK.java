package com.example.beauty_salon.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class UsersHasRoleEntityPK implements Serializable {
    private int usersIdUser;

    private int roleIdRole;

    public UsersHasRoleEntityPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersHasRoleEntityPK that = (UsersHasRoleEntityPK) o;
        return usersIdUser == that.usersIdUser && roleIdRole == that.roleIdRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersIdUser, roleIdRole);
    }
}
