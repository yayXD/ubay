package world.ucode.pojo;

import javax.persistence.*;
import java.security.SecureRandom;
import java.sql.SQLTransactionRollbackException;

@Entity
@Table(name = "Registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", updatable = false, nullable = false)
    private int id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "state", updatable = true)
    private String state;
    @Column(name = "IPadress", updatable = true)
    private String IPadress;
    @Column(name = "email", nullable = false, updatable = true)
    private String email;
    @Column(name = "hash", nullable = false, updatable = true)
    private int hash;

    public Registration(){}

    public Registration(int id, String login, String password, String role, String state, String IPadress, String email,
                        int hash){
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.state = state;
        this.IPadress = IPadress;
        this.email = email;
        this.hash = hash;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public String getState() {
        return state;
    }
    public String getIPadress() {
        return IPadress;
    }
    public String getEmail() {
        return email;
    }
    public int getHash() {
        return hash;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setIPadress(String iPadress) {
        this.IPadress = iPadress;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHash(int hash) {
        this.hash = hash;
    }

}

