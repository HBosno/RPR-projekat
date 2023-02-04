package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Profile;

import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class ProfilesDaoSQLImpl extends AbstractDao<Profile> implements ProfilesDao{

    public ProfilesDaoSQLImpl(String tableName) {
        super(tableName);
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
