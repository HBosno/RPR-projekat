package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Business logic layer for managing cards.
 * @author Hamza Bosno
 */
public class CardManager {

    /**
     * Return all cards associated with current user's account.
     * @param id - user id
     * @return list of cards
     */
    public List<Card> getUserCards(int id) throws AppException {
        return DaoFactory.cardsDao().getAllByProfileId(id);
    }
}
