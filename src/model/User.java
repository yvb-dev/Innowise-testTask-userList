package model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class User extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private RoleLevel1 roleLevel1;
    private RoleLevel2 roleLevel2;
    private RoleLevel3 roleLevel3;
    private List<String> mobile;

    public User(Integer id) {
        super(id);
    }

    public User() {
        // only console app. not need for storage in db.
        super(UUID.randomUUID().hashCode() & Integer.MAX_VALUE);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public RoleLevel1 getRoleLevel1() {
        return roleLevel1;
    }

    public RoleLevel2 getRoleLevel2() {
        return roleLevel2;
    }

    public RoleLevel3 getRoleLevel3() {
        return roleLevel3;
    }

    public List<String> getMobile() {
        return mobile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleLevel1(RoleLevel1 roleLevel1) {
        this.roleLevel1 = roleLevel1;
    }

    public void setRoleLevel2(RoleLevel2 roleLevel2) {
        this.roleLevel2 = roleLevel2;
    }

    public void setRoleLevel3(RoleLevel3 roleLevel3) {
        this.roleLevel3 = roleLevel3;
    }

    public void setMobile(List<String> mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", roleLevel1=" + roleLevel1 +
                ", roleLevel2=" + roleLevel2 +
                ", roleLevel3=" + roleLevel3 +
                ", mobile=" + mobile +
                ')';
    }
}
