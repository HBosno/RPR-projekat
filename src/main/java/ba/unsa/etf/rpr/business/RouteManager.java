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

    /**
     * Method for retrieving the route with specified name.
     * @param name - route name
     * @return route bean
     */
    public Route getByName(String name) throws AppException {
        return DaoFactory.routesDao().searchByName(name);
    }

    /**
     * Get route with specified id.
     * @param id - primary key of route
     * @return route
     */
    public Route getById(int id) throws AppException {
        return DaoFactory.routesDao().getById(id);
    }
}
