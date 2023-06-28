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

    /**
     * Validation method used on login screen. Used to check if there is an existing user in database based on email provided.
     * After that, checks if entered password matches the one in database for found user.
     * @param email - email user entered on login
     * @param password - password user entered on login
     * @return true if user exists in database, false otherwise
     */
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

    /**
     * Method used to execute sql query for adding a profile to database on registration.
     * @param name - entered name on registration screen
     * @param surname - entered surname on registration screen
     * @param password - entered password on registration screen
     * @param email - entered email on registration screen
     */
    public void addToDatabase(String name, String surname, String password, String email){
        try{
            DaoFactory.profilesDao().add(new Profile(-1, name, surname, password, email, "", ""));
        }
        catch(AppException e){
            e.printStackTrace();
        }
    }

}
