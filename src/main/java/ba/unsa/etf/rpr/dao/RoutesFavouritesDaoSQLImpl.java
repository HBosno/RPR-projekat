package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class RoutesFavouritesDaoSQLImpl extends AbstractDao<RouteFavourite> implements RoutesFavouritesDao {
    private static RoutesFavouritesDaoSQLImpl instance = null;
    private RoutesFavouritesDaoSQLImpl() {
        super("routes_favourites");
    }

    public static RoutesFavouritesDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RoutesFavouritesDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public RouteFavourite row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(RouteFavourite object) {
        return null;
    }
}
