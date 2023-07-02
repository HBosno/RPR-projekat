package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.RouteFavourite;
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

public class RoutesFavouritesDaoSQLImpl extends AbstractDao<RouteFavourite> implements RoutesFavouritesDao {
    private static RoutesFavouritesDaoSQLImpl instance = null;
    private RoutesFavouritesDaoSQLImpl() {
        super("routesFavourites");
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

    /**
     * Method for mapping a record in table to corresponding RouteFavourite bean object.
     * @param rs - result set from database
     * @return RouteFavourite bean
     */
    @Override
    public RouteFavourite row2object(ResultSet rs) throws AppException{
        try {
            RouteFavourite routeFavourite = new RouteFavourite();
            routeFavourite.setId(rs.getInt("id"));
            routeFavourite.setProfile(DaoFactory.profilesDao().getById(rs.getInt("profile_id")));
            routeFavourite.setRoute(DaoFactory.routesDao().getById(rs.getInt("route_id")));
            return routeFavourite;
        } catch (SQLException e) {
            throw new AppException(e.getMessage(), e);
        }
    }

    /**
     * Method for mapping a RouteFavourite bean object to corresponding table record.
     * @param object - a RouteFavourite object for specific table
     * @return map containing columns with respective values, keys being column names
     */
    @Override
    public Map<String, Object> object2row(RouteFavourite object) {
        Map<String, Object> map = new TreeMap<>();
        map.put("id", object.getId());
        map.put("profile_id", object.getProfile().getId());
        map.put("route_id", object.getRoute().getId());
        return map;
    }

    /**
     * Method executing sql query for checking if a certain route has been added to favourites by specified user.
     * @param profileId - user id
     * @param routeId - route id
     * @return true if route is in favourites, false otherwise
     */
    @Override
    public boolean checkForRoute(int profileId, int routeId){
        try{
            executeQueryUnique("SELECT * FROM routesFavourites WHERE profile_id = ? AND route_id = ?", new Object[]{profileId, routeId});
            return true;
        }
        catch(AppException e){
            return false;
        }
    }

    /**
     * Method executing sql query for retrieving route with specified user.
     * @param profileId - user id
     * @param routeId - route id
     * @return route
     */
    @Override
    public RouteFavourite getRoute(int profileId, int routeId) throws AppException {
        return executeQueryUnique("SELECT * FROM routesFavourites WHERE profile_id = ? AND route_id = ?", new Object[]{profileId, routeId});
    }

    /**
     * Method executing sql query for retrieving all favourite routes for specified user.
     * @param userId - user id
     * @return list of routes
     */
    @Override
    public List<RouteFavourite> getAllForUser(int userId) throws AppException {
        return executeQuery("SELECT * FROM routesFavourites WHERE profile_id = ?", new Object[]{userId});
    }
}
