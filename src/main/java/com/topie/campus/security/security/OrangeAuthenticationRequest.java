package com.topie.campus.security.security;


public class OrangeAuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515507L;
    private String username;
    private String password;

    public OrangeAuthenticationRequest() {
    }

    public OrangeAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
