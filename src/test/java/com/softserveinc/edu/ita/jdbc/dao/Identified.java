package jdbc.dao;

import java.io.Serializable;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public interface Identified<PK extends Serializable> {

    public PK getId();
}
