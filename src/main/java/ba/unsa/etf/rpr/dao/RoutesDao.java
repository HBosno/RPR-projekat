package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

/**
 * Dao interface for Routes domain bean
 *
 * @author Hamza Bosno
 */

public interface RoutesDao extends Dao<Route> {

    /**
     * Returns a list of routes starting at point sent in the parameter.
     *
     * @param start starting point of the route
     * @return list of routes
     */
    List<Route> searchByStartingPoint(String start) throws AppException;

}
