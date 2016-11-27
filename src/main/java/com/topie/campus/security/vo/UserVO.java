package com.topie.campus.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topie.campus.security.model.User;

/**
 * Created by cgj on 2015/10/30.
 */
public class UserVO extends User {
    @JsonIgnore
    public static User buildSimpleUser(String employeeNo, String contactPhone, String password, String name, String email) {
        User user = new User();
        user.setLoginName(employeeNo);
        user.setPassword(password);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setContactPhone(contactPhone);
        user.setEmail(email);
        user.setDisplayName(name);
        user.setCredentialsNonExpired(true);
        return user;
    }

    @JsonIgnore
    public static User buildSimpleUser(String loginName,String mobile, String name, String email) {
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(mobile);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setContactPhone(mobile);
        user.setEmail(email);
        user.setDisplayName(name);
        user.setCredentialsNonExpired(true);
        return user;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return "";
    }
}
