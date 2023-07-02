package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.dao.DaoFactory;

/**
 * Business logic layer for managing favourite routes.
 * @author Hamza Bosno
 */
public class RouteFavouriteManager {
    /**
     * Method for checking if a route has been added to favourites.
     * @param profileId - user id
     * @param routeId - route id
     * @return true if route is favourite, false otherwise
     */
    public boolean checkForRoute(int profileId, int routeId){
        return DaoFactory.routesFavouritesDao().checkForRoute(profileId, routeId);
    }
}
