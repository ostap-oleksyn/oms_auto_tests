package com.softserveinc.edu.ita.dao_jdbc.classes;

import com.softserveinc.edu.ita.dao_jdbc.interfaces.IIdentified;

/**
 * Created by Ihor-Dynka on 05.06.2015.
 */
public class Role implements IIdentified <Integer> {
   private  Integer id;
   private String roleName;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
