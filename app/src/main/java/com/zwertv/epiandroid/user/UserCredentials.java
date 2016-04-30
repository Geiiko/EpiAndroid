package com.zwertv.epiandroid.user;

/**
 * Created by Elliott on 28/01/2016.
 */
public class UserCredentials {
    private String login;
    private String token;

    public void setLogin(String login) { this.login = login; }
    public String getLogin() { return login; }
    public void setToken(String token) { this.token = token; }
    public String getToken() { return token; }

    public String toString() {
        return "Login: " + login + " - token: '" + token + "'";
    }
}
