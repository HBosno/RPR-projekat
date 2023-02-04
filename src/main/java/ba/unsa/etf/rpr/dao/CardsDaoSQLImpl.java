package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;

import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class CardsDaoSQLImpl extends  AbstractDao<Card> implements CardsDao{

    @Override
    public Card row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Card object) {
        return null;
    }
}
