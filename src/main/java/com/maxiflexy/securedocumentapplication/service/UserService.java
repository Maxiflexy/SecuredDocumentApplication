package com.maxiflexy.securedocumentapplication.service;

import com.maxiflexy.securedocumentapplication.entity.RoleEntity;

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
}
