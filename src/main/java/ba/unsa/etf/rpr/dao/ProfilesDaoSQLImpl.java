package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public Profile row2object(ResultSet rs) throws AppException{
        try {
            Profile profile = new Profile();
            profile.setId(rs.getInt("id"));
            profile.setName(rs.getString("name"));
            profile.setSurname(rs.getString("surname"));
            profile.setPassword(rs.getString("password"));
            profile.setEmail(rs.getString("email"));
            profile.setAdress(rs.getString("adress"));
            profile.setTelephoneNumber(rs.getString("telephone_number"));
            return profile;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Profile object) {
        Map<String, Object> map = new TreeMap<>();
        map.put("id", object.getId());
        map.put("name", object.getName());
        map.put("surname", object.getSurname());
        map.put("password", object.getPassword());
        map.put("email", object.getEmail());
        map.put("adress", object.getAdress());
        map.put("telephone_number", object.getTelephoneNumber());
        return map;
    }

    /**
     * Method executing an sql query for retrieving a profile with specified email attribute.
     * @param email - email attribute of profile bean
     * @return list of requested records, size is 0 or 1
     */
    @Override
    public List<Profile> findProfileByEmail(String email) throws AppException {
        try{
            return executeQuery("SELECT * FROM profiles WHERE email = ?", new Object[]{email});
        }
        catch (AppException e) {
            e.printStackTrace();
            return null;
        }
    }
}
