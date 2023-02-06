package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class CardsDaoSQLImpl extends AbstractDao<Card> implements CardsDao{

    private static CardsDaoSQLImpl instance = null;
    private CardsDaoSQLImpl() {
        super("cards");
    }

    public static CardsDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new CardsDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Card row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Card object) {
        return null;
    }
}
