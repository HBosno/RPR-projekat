package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
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

    /**
     * Testing getUserName() method. Adds a user to database, then calls the method and checks if correct username is returned.
       Profile is deleted from db on completion.
     */
    @Test
    void getUserName() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        assertEquals(profileManager.getUserName("hpipa1@etf.unsa.ba"), profile.getName());
        DaoFactory.profilesDao().delete(profile.getId());
    }

    /**
     * Testing getUserName() method. Calls the method with an email that isn't registered in database and checks if null is returned.
     */
    @Test
    void getUserNameFail() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        assertNull(profileManager.getUserName("randomemail1231###$$@msn.com"));
    }

    /**
     * Test for updateProfile() and getById() methods.
     */
    @Test
    void updateProfileAndGetById() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        profile.setName("Hamo");
        profile.setSurname("Pipa");
        profileManager.updateProfile(profile);
        Profile updatedProfile = profileManager.getById(profile.getId());
        assertEquals("Hamo", updatedProfile.getName());
        assertEquals("Pipa", updatedProfile.getSurname());
        DaoFactory.profilesDao().delete(profile.getId());
    }

    /**
     * Test for getProfileByEmail() method.
     */
    @Test
    void getProfileByEmail() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profile = profileManager.addToDatabase("hamo", "pipa", "123", "hpipa1@etf.unsa.ba");
        Profile profileFoundByEmail = profileManager.getProfileByEmail("hpipa1@etf.unsa.ba");
        assertEquals(profileFoundByEmail, profile);
        DaoFactory.profilesDao().delete(profile.getId());
    }

    /**
     * Tests getProfileByEmail() method with an invalid email.
     */
    @Test
    void getProfileByEmailUnsuccessful() throws AppException {
        ProfileManager profileManager = new ProfileManager();
        Profile profileFoundByEmail = profileManager.getProfileByEmail("randomemail1231###$$@msn.com");
        assertNull(profileFoundByEmail);
    }

}