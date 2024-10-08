package org.ntnu.rest.model;

public class UserLogin {
    private String username;
    private String password;

    public UserLogin() {}

    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "Password: " + password;
    }
}