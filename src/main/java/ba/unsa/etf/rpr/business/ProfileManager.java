package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Business logic layer for managing profiles.
 * @author Hamza Bosno
 */
public class ProfileManager {

    public boolean validateLogin(String email, String password){
        List<Profile> profiles;
        try{
            profiles = DaoFactory.profilesDao().findProfileByEmail(email);
        }
        catch(AppException e){
            e.printStackTrace();
            return false;
        }
        if(profiles.isEmpty()){
            return false;
        }
        Profile profile = profiles.get(0);
        return profile.getPassword().equals(password);
    }

}
