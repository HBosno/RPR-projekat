package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.exceptions.AppException;

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

    /**
     * Method for adding a route to favourites.
     * @param route - route bean to be added
     */
    public void addFavourite(RouteFavourite route) throws AppException {
        DaoFactory.routesFavouritesDao().add(route);
    }

    /**
     * Get favourite route by profile and route id.
     * @param profileId - user id
     * @param routeId - route id
     * @return favourite route bean
     */
    public RouteFavourite getRoute(int profileId, int routeId) throws AppException {
        return DaoFactory.routesFavouritesDao().getRoute(profileId, routeId);
    }

    /**
     * Delete route with specified id from favourites.
     * @param id - favourite route id
     */
    public void deleteRoute(int id) throws AppException {
        DaoFactory.routesFavouritesDao().delete(id);
    }
}
