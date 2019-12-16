package com.oauth.fancyfrog.persistence;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */
public enum AuthProvider {

    LOCAL("local"),FACEBOOK("facebook"),GOOGLE("google"),GITHUB("github");

    private final String provider;

    private AuthProvider(String provider){
        this.provider = provider;
    }

}
