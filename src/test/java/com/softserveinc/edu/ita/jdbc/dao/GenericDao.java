package jdbc.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
public interface GenericDao <T extends Identified <PK>, PK extends Serializable> {

    public T getById(PK key) throws PersistException;

    public T getByLogin(String login) throws PersistException;

    public List<T> getAll() throws PersistException;

    public T getByLogin(PK login) throws PersistException;
}
