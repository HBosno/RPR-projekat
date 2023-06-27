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
    List<Profile> findProfileByEmail(String email) throws AppException;
}
