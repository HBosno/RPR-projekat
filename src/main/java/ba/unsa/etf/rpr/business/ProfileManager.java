package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

public class ProfileManager {

    public boolean validateLogin(String email, String password){
        Profile profile;
        try{
            profile = DaoFactory.profilesDao().findProfileByEmail(email);
        }
        catch(AppException e){
            return false;
        }
        return profile.getPassword() == password;
    }

}
