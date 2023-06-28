package ba.unsa.etf.rpr.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean class modelling a single bus route
 *
 * @author Hamza Bosno
 */

public class Route implements Idable, Serializable{

    private int id;
    private String route;
    private String frequency;

    public Route(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route=" + route +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, frequency);
    }
}
