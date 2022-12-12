package by.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String login;
    private String password;
    private String role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int userID, String login, String password, String role) {
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
