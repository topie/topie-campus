package com.topie.campus.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topie.campus.security.model.User;

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
