package dao.domain;

import javax.persistence.*;

/**
 * Created by alex on 9/2/15.
 */
@Entity
public class UsersPrivateData {

    @Id
    @GeneratedValue
    private int usersDataId;

    @ManyToOne
    @JoinColumn(name = "usersId")
    private Users user;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String pass;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    public int getUsersDataId() {
        return usersDataId;
    }

    public void setUsersDataId(int usersDataId) {
        this.usersDataId = usersDataId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
