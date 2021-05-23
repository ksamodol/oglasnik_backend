package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.location.County;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAll();
    List<Place> findAllByCounty_id(Long id);
    Optional<Place> findByNameEquals(String name);
}
