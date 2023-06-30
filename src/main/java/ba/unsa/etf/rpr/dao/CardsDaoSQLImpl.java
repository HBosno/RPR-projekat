package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.CardType;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    /**
     * Method for mapping a record in table to corresponding Card bean object.
     * @param rs - result set from database
     * @return Card bean
     */
    @Override
    public Card row2object(ResultSet rs) throws AppException {
        try {
            Card card = new Card();
            card.setId(rs.getInt("id"));
            card.setSerialNumber(rs.getInt("serial_number"));
            card.setCardType(CardType.valueOf(rs.getString("card_type")));
            card.setBalance(rs.getDouble("balance"));
            card.setMonthlyCoupon(rs.getBoolean("monthly_coupon"));
            card.setProfile(DaoFactory.profilesDao().getById(rs.getInt("profile_id")));
            return card;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    /**
     * Method for mapping a card bean object to corresponding table record.
     * @param object - a bean object for specific table
     * @return map containing columns with respective values, keys being column names
     */
    @Override
    public Map<String, Object> object2row(Card object) {
        Map<String, Object> map = new TreeMap<>();
        map.put("id", object.getId());
        map.put("serial_number", object.getSerialNumber());
        map.put("card_type", object.getCardType().toString());
        map.put("balance", object.getBalance());
        map.put("monthly_coupon", object.isMonthlyCoupon());
        map.put("profile_id", object.getProfile().getId());
        return map;
    }

    /**
     * Method executing an sql query for retrieving a card with specified serial number attribute.
     * @param serialno - card serial number to search for
     * @return requested Card bean
     */
    @Override
    public Card searchBySerialNumber(String serialno) throws AppException{
        try{
            return executeQuery("SELECT * FROM cards WHERE serial_number = ?", new Object[]{serialno}).get(0);
        }
        catch(AppException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Card> getAllByProfileId(int id){
        try{
            return executeQuery("SELECT * FROM cards WHERE profile_id = ?", new Object[]{id});
        }
        catch(AppException e){
            e.printStackTrace();
            return null;
        }
    }

}
