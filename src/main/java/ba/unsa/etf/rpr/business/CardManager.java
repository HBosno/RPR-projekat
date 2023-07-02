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

    /**
     * Return card with specified serial number.
     * @param serialno - card number
     * @return card
     */
    public Card getCard(int serialno) throws AppException {
        return DaoFactory.cardsDao().searchBySerialNumber(serialno);
    }

    /**
     * Delete card with specified id.
     * @param id - card id
     */
    public void deleteCard(int id) throws AppException {
        DaoFactory.cardsDao().delete(id);
    }

    /**
     * Update card with specified id.
     * @param card = card bean to be updated
     */
    public void updateCard(Card card) throws AppException {
        DaoFactory.cardsDao().update(card);
    }

    /**
     * Add provided card to database.
     * @param card - provided card bean
     */
    public void addCard(Card card) throws AppException {
        DaoFactory.cardsDao().add(card);
    }
}
