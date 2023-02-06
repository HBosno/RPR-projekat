package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class ProfilesDaoSQLImpl extends AbstractDao<Profile> implements ProfilesDao{

    private static ProfilesDaoSQLImpl instance = null;
    private ProfilesDaoSQLImpl() {
        super("profiles");
    }

    public static ProfilesDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ProfilesDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Profile row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Profile object) {
        return null;
    }

}
