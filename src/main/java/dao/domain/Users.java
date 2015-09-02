package dao.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity bean with JPA annotations
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

//    @Column(nullable = false)
//    private String pass;
//
//    @Enumerated(EnumType.STRING)
//    private RoleEnum role;


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

//    public String getPass() {
//        return pass;
//    }
//
//    public RoleEnum getRole() {
//        return role;
//    }

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

//    public void setPass(String pass) {
//        this.pass = pass;
//    }
//
//    public void setRole(RoleEnum role) {
//        this.role = role;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users user = (Users) o;

        //if (userId != user.userId) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null)
            return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
//        if (pass != null ? !pass.equals(user.pass) : user.pass != null)
//            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
//        int result = idstudents;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
//        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
