package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Hamza Bosno
 */

public class DaoFactory {

    private static final CardsDao cardsDao = CardsDaoSQLImpl.getInstance();
    private static final ProfilesDao profilesDao = ProfilesDaoSQLImpl.getInstance();
    private static final RoutesDao routesDao = RoutesDaoSQLImpl.getInstance();
    private static final RoutesFavouritesDao routesFavouritesDao = RoutesFavouritesDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static CardsDao cardsDao(){
        return cardsDao;
    }

    public static ProfilesDao profilesDao(){
        return profilesDao;
    }

    public static RoutesDao routesDao(){
        return routesDao;
    }

    public static RoutesFavouritesDao routesFavouritesDao(){
        return routesFavouritesDao;
    }

}
