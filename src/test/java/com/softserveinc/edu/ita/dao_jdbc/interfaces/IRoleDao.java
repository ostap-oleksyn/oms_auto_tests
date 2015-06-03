package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import com.softserveinc.edu.ita.dao_jdbc.Classes.Role;

import java.util.List;

/**
 * Created by student on 6/3/2015.
 */
public interface IRoleDao  {

    public List<Role> getUsersByRole(String roleName);
//    public List<Role> getUsersByRole();
}
