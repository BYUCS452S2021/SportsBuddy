package Models;

import java.util.Objects;

public class Location {
    private String locationId;
    private String city;
    private String state;
    private String country;
    private int zipCode;

    public Location(String locationId, String city, String state, String country, int zipCode) {
        this.locationId = locationId;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
    public Location() {

    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) &&
                Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(country, location.country) &&
                Objects.equals(zipCode, location.zipCode);
    }
}
