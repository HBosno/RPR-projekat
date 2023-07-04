package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ProfilesDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileManagerTest {

    /**
     * Testing validateLogin() method. Adds a user to database then tests if validate returns true as it should, now that the
       user entered correct email and password. Finally, deletes user from database so that test can be run again. We also test
       addToDatabase() function along the way in these validatelogin tests.
     */
    @Test
    void validateLoginSuccessful() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        assertTrue(profileManager.validateLogin("hpipa1@etf.unsa.ba", "123"));
        DaoFactory.profilesDao().delete(profile.getId());
    }

    /**
     * Testing validateLogin() method. Adds a user to database then tests if validate returns false as it should, now that the
       user entered wrong password on login screen. Finally, deletes user from database so that test can be run again.
     */
    @Test
    void validateLoginWrongPassword() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        assertFalse(profileManager.validateLogin("hpipa1@etf.unsa.ba", "123456456"));
        DaoFactory.profilesDao().delete(profile.getId());
    }

    /**
     * Testing validateLogin() method. Adds a user to database then tests if validate returns false as it should, now that the
     user entered wrong email on login screen. Finally, deletes user from database so that test can be run again.
     */
    @Test
    void validateLoginWrongEmail() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        assertFalse(profileManager.validateLogin("hpipaaaa1@etf.unsa.ba", "123"));
        DaoFactory.profilesDao().delete(profile.getId());
    }

}