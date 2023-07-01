package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Dao interface for Cards domain bean
 *
 * @author Hamza Bosno
 */

public interface CardsDao extends Dao<Card>{

    /**
     * Return the card with the given serial number.
     *
     * @param serialno card serial number to search for
     * @return requested card
     */

    Card searchBySerialNumber(int serialno) throws AppException;

    /**
     * Return all cards sharing same profile id.
     * @param id - profile foreign key
     * @return list of cards
     */
    List<Card> getAllByProfileId(int id) throws AppException;

}
