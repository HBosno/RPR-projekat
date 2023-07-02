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
     * Returns the route with specified route name.
     *
     * @param routeName - name of the route
     * @return route bean
     */
    Route searchByName(String routeName) throws AppException;

}
