package com.formAuthentication.persistence.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */
public enum Role {

    ADMIN,USER,MANAGER;

    public String authority(){
        return "ROLE_" + this.name();
    }

    public List<Permission> getPermissions(Role role){
        switch (role){
            case ADMIN :
                return Arrays.asList(Permission.READ_PERMISSION,Permission.WRITE_PERMISSION,Permission.ACCESS_TEST1,Permission.ACCESS_TEST2);
            case MANAGER:
                return Arrays.asList(Permission.READ_PERMISSION,Permission.ACCESS_TEST1);
            default:
                return Arrays.asList(Permission.READ_PERMISSION);
        }
    }
}
