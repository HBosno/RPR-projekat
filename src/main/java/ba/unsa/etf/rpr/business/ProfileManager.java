package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import com.mysql.cj.log.ProfilerEvent;

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
        Profile profile;
        try{
            profile = DaoFactory.profilesDao().findProfileByEmail(email);
        }
        catch(AppException e){
            e.printStackTrace();
            return false;
        }
        return profile.getPassword().equals(password);
    }

    /**
     * Method used to execute sql query for adding a profile to database on registration.
     * @param name - entered name on registration screen
     * @param surname - entered surname on registration screen
     * @param password - entered password on registration screen
     * @param email - entered email on registration screen
     */
    public Profile addToDatabase(String name, String surname, String password, String email) throws AppException {
        return DaoFactory.profilesDao().add(new Profile(-1, name, surname, password, email, null, null));
    }

    /**
     * Method for retrieving user's name using his email adress.
     * @param email - user's email adress
     * @return username
     */
    public String getUserName(String email){
        try {
            Profile user = DaoFactory.profilesDao().findProfileByEmail(email);
            return user.getName();
        }
        catch (AppException e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Simple method for fetching a profile based on email. Simply calls findProfileByEmail method and returns the first and only element.
     * @param email - user's email
     * @return user profile
     */
    public Profile getProfileByEmail(String email){
        try {
            return DaoFactory.profilesDao().findProfileByEmail(email);
        }
        catch(AppException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Simple method that calls update CRUD method for updating user's personal information.
     * @param user - user profile to be updated
     */
    public void updateProfile(Profile user){
        try{
            DaoFactory.profilesDao().update(user);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve profile by its primary key.
     * @param id - profile id
     * @return profile bean
     */
    public Profile getById(int id) throws AppException {
        return DaoFactory.profilesDao().getById(id);
    }

}
