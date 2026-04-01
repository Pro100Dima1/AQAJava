package org.reshebnik.collection.ex2;

import java.util.Objects;

public class Cities {
    private String nameOfCity;

    public Cities(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "nameOfCity='" + nameOfCity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cities)) return false;
        Cities cities = (Cities) o;
        return Objects.equals(nameOfCity, cities.nameOfCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfCity);
    }
}
