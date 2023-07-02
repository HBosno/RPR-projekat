package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RouteFavourite;

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
}
