/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author azul_
 */
public class Members {
    public String name;
    public String lastName;
    public String position;
    public String birthday;
    public String curp;
    public String email;
    public String password;

    public Members() {
    }

    public Members(String name, String lastName, String position, String birthday, String curp, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.birthday = birthday;
        this.curp = curp;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCurp() {
        return curp;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
}
