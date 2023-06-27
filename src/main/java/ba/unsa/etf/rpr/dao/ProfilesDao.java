package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

/**
 * Dao interface for Profiles domain bean
 *
 * @author Hamza Bosno
 */

public interface ProfilesDao extends Dao<Profile> {
    Profile findProfileByEmail(String email) throws AppException;
}
