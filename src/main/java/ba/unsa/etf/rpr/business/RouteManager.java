package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Business logic layer for managing routes.
 * @author Hamza Bosno
 */
public class RouteManager {

    /**
     * Method for retrieving all routes from database.
     * @return list of routes
     */
    public List<Route> getAllRoutes() throws AppException {
        return DaoFactory.routesDao().getAll();
    }
}
