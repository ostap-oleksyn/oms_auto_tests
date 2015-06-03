package com.softserveinc.edu.ita.dao_jdbc.Classes;

/**
 * Created by student on 6/3/2015.
 */
public class Role  {
    private Integer id;
    private String roleName;

    public Role(Integer id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
