package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.domain.TimeTable;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;

public class TransportSystemFacade {
    private RouteFavouriteManager routeFavouriteManager;
    private ProfileManager profileManager;
    private TimeTableGenerator timeTableGenerator;

    public TransportSystemFacade(RouteFavouriteManager routeFavouriteManager, ProfileManager profileManager, TimeTableGenerator timeTableGenerator) {
        this.routeFavouriteManager = routeFavouriteManager;
        this.profileManager = profileManager;
        this.timeTableGenerator = timeTableGenerator;
    }

    public List<RouteFavourite> getUserFavouriteRoutes(int userId) throws AppException {
        return routeFavouriteManager.getAllForUser(userId);
    }

    public void deleteRoute(int routeId) throws AppException {
        routeFavouriteManager.deleteRoute(routeId);
    }

    public String getUserEmail(int userId) throws AppException {
        return profileManager.getById(userId).getEmail();
    }

    public List<TimeTable> generateTimeTable(String frequency, boolean isWeekend) throws AppException {
        return timeTableGenerator.generate(300, 1380, frequency, isWeekend);
    }
}

