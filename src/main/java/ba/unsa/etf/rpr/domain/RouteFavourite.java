package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * List of routes of interest for certain profiles
 *
 * @author Hamza Bosno
 */

public class RouteFavourite implements Idable, Serializable{

    private int id;
    private Profile profile;
    private Route route;

    public RouteFavourite(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "RouteFavourite{" +
                "id=" + id +
                ", profile=" + profile +
                ", route=" + route +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteFavourite routefavourite = (RouteFavourite) o;
        return id == routefavourite.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profile, route);
    }
}
