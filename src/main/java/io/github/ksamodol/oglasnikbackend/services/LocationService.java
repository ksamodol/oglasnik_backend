package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.location.CountyDTO;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.entity.location.PlaceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {
    List<PlaceDTO> findAllPlaces();
    List<PlaceDTO> findAllPlacesByCountyId(Long id);
    List<CountyDTO> findAllCounties();
}
