package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Dao interface for RoutesFavourites domain bean
 *
 * @author Hamza Bosno
 */

public interface RoutesFavouritesDao extends Dao<RouteFavourite>{
    /**
     * Method that will check if a certain user has already added a route as his favourite.
     * @param profileId - user id
     * @param routeId - route id
     * @return true if route is favourite, false otherwise
     */
    boolean checkForRoute(int profileId, int routeId);

    /**
     * Retrieve route associated with specified user.
     * @param profileId - user id
     * @param routeId - route id
     * @return route
     */
    RouteFavourite getRoute(int profileId, int routeId) throws AppException;

    /**
     * Retrieves all favourite routes for specified user.
     * @param userId - user id
     * @return - list of routes
     */
    List<RouteFavourite> getAllForUser(int userId) throws AppException;
}
