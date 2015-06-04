package jdbc.domain;

import jdbc.dao.Identified;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public class Role implements Identified<Integer> {
    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

}
