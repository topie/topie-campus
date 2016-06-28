package com.orange.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orange.security.model.User;

/**
 * Created by cgj on 2015/10/30.
 */
public class UserVO extends User {
    @Override
    @JsonIgnore
    public String getPassword() {
        return "";
    }
}
