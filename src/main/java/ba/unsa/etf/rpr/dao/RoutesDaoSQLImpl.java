package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 * @author Hamza Bosno
 */

public class RoutesDaoSQLImpl extends AbstractDao<Route> implements RoutesDao {
    private static RoutesDaoSQLImpl instance = null;
    private RoutesDaoSQLImpl() {
        super("routes");
    }

    public static RoutesDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new RoutesDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Route row2object(ResultSet rs) throws AppException{
        try {
            Route route = new Route();
            route.setId(rs.getInt("id"));
            route.setRoute(rs.getString("route"));
            route.setFrequency(rs.getString("frequency"));
            return route;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Route object) {
        Map<String, Object> map = new TreeMap<>();
        map.put("id", object.getId());
        map.put("route", object.getRoute());
        map.put("frequency", object.getFrequency());
        return map;
    }

}
