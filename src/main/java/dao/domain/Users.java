package dao.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alex on 7/16/15.
 */
@Entity
public class Users implements Serializable{

    @Id
    @GeneratedValue
    private int userId;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String pass;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    public Users() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
