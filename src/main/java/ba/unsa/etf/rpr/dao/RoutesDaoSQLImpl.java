package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Route;

import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class RoutesDaoSQLImpl extends AbstractDao<Route> implements RoutesDao {
    @Override
    public Route row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Route object) {
        return null;
    }
}
