package io.github.ksamodol.oglasnikbackend.entity.location;

public class CountyDTO {
    private Long id;
    private String name;

    public CountyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
