package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;

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

    Card searchBySerialNumber(String serialno);

}
