package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RouteFavourite;

import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class RoutesFavouritesDaoSQLImpl extends AbstractDao<RouteFavourite> implements RoutesFavouritesDao {
    public RoutesFavouritesDaoSQLImpl(String tableName) {
        super(tableName);
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
