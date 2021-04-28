package io.github.ksamodol.oglasnikbackend.entity.location;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "county")
public class County {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "county", fetch=FetchType.EAGER)
    private List<Place> placeList;


    public County() {
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Place> getPlaceList() {
        return placeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county = (County) o;
        return id == county.id &&
                Objects.equals(name, county.name) &&
                Objects.equals(placeList, county.placeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, placeList);
    }
}
