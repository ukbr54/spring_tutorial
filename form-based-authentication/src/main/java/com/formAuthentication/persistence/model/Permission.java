package com.formAuthentication.persistence.model;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */
public enum Permission {

    READ_PERMISSION, WRITE_PERMISSION,ACCESS_TEST1,ACCESS_TEST2;

    public String getPermission(){
        return this.name();
    }
}
