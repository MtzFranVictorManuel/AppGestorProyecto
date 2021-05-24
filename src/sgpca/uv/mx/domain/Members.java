package sgpca.uv.mx.domain;

import java.sql.Date;

/**
 *
 * @author azul_
 */
public class Members {
    public static int idMember;
    public static String name;
    public static String lastName;
    public static String position;
    public static Date birthday;
    public static String curp;
    public static String email;
    public static String password;

    public Members() {
    }

    public Members(int idMember) {
        Members.idMember = idMember;
    }

    public Members(String name, String lastName, String position, Date birthday, String curp, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.birthday = birthday;
        this.curp = curp;
        this.email = email;
        this.password = password;
    }

    
    
    public int getIdMember() {
        return idMember;
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

    public Date getBirthday() {
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

    public void setIdMember(int idMember) {
        this.idMember = idMember;
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

    public void setBirthday(Date birthday) {
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
