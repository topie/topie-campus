package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_user_faculty")
public class UserFaculty {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    private String faculty;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * @param faculty
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}