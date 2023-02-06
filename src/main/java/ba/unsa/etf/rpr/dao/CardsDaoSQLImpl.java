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
    public Card row2object(ResultSet rs) throws AppException {
        try {
            Card card = new Card();
            card.setId(rs.getInt("id"));
            card.setSerialNumber(rs.getInt("serial_number"));
            card.setCardType(rs.getString("card_type"));
            card.setBalance(rs.getDouble("balance"));
            card.setMonthlyCoupon(rs.getBoolean("monthly_coupon"));
            card.setProfile(DaoFactory.profilesDao().getById(rs.getInt("profile_id")));
            return card;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Card object) {
        Map<String, Object> map = new TreeMap<>();
        map.put("id", object.getId());
        map.put("serial_number", object.getSerialNumber());
        map.put("card_type", object.getCardType());
        map.put("balance", object.getBalance());
        map.put("monthly_coupon", object.isMonthlyCoupon());
        map.put("profile_id", object.getProfile().getId());
        return map;
    }

    public Card searchBySerialNumber(String serialno){
        try {
            return (Card) executeQuery("SELECT * FROM cards WHERE serial_number = ?", new Object[]{serialno});
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

}
