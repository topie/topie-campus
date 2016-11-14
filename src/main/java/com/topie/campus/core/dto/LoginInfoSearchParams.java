package com.topie.campus.core.dto;

import java.util.List;

/**
 * Created by chenguojun on 2016/11/13.
 */
public class LoginInfoSearchParams {

    private String loginTimeFrom;

    private String loginTimeTo;

    private List<Integer> userIds;

    public String getLoginTimeFrom() {
        return loginTimeFrom;
    }

    public void setLoginTimeFrom(String loginTimeFrom) {
        this.loginTimeFrom = loginTimeFrom;
    }

    public String getLoginTimeTo() {
        return loginTimeTo;
    }

    public void setLoginTimeTo(String loginTimeTo) {
        this.loginTimeTo = loginTimeTo;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
