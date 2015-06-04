package jdbc.dao;

/**
 * Created by Ihor-Dynka on 04.06.2015.
 */
    public interface DaoFactory<Context> {

        public interface DaoCreator<Context> {
            public GenericDao create(Context context);
        }


        public Context getContext() throws PersistException;

        public GenericDao getDao(Context context, Class Class) throws PersistException;
}

