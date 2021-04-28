package io.github.ksamodol.oglasnikbackend.entity.location;

public class PlaceDTO {
    private int id;
    private String name;

    public PlaceDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
