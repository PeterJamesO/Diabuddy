package whsct.test;

import java.util.List;


public class UserData {
    // Object values
    private long id;
    private String username;
    private String password;
    private String email;
    private String number;
    private String active;

    @Override
    public String toString() { return
            "ID:           \t" + this.id +
            "\nUsername:   \t" + this.username +
            "\nPassword:   \t" + this.password +
            "\nDr's Email: \t" + this.email +
            "\nDr's Number:\t" + this.number +
            "\nIs Active:  \t" + this.active;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }
}
