package io.github.ksamodol.oglasnikbackend.entity.location;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "place")
public class Place {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="countyId")
    private County county;

    @Column(name = "placeType")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    public Place() {
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public County getCounty() {
        return county;
    }
    public PlaceType getPlaceType() {
        return placeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                Objects.equals(name, place.name) &&
                Objects.equals(county, place.county) &&
                placeType == place.placeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, county, placeType);
    }
}
