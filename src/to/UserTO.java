package to;

import model.AbstractBaseEntity;

import java.util.UUID;

public class UserTO extends AbstractBaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String editEmail;
    private String roles;
    private String mobile;

    public UserTO() {
        super(UUID.randomUUID().hashCode() & Integer.MAX_VALUE);
    }

    public UserTO(Integer id) {
        super(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEditEmail() {
        return editEmail;
    }

    public void setEditEmail(String editEmail) {
        this.editEmail = editEmail;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", roles=" + roles +
                ", mobile=" + mobile +
                ')';
    }
}
