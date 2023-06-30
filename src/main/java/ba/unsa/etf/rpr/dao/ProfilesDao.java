package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Dao interface for Profiles domain bean
 *
 * @author Hamza Bosno
 */

public interface ProfilesDao extends Dao<Profile> {
    /**
     * Method executing an sql query for retrieving a profile with specified email attribute.
     * @param email - email attribute of profile bean
     * @return list of requested records, size is 0 or 1
     */
    Profile findProfileByEmail(String email) throws AppException;
}
