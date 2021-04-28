package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.entity.location.PlaceDTO;
import io.github.ksamodol.oglasnikbackend.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final PlaceRepository placeRepository;

    public LocationServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<PlaceDTO> findAllPlaces() {
        return placeRepository.findAll().stream().map(this::mapPlaceToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PlaceDTO> findAllPlacesByCountyId(int id){
        return placeRepository.findAllByCounty_id(id).stream().map(this::mapPlaceToDTO).collect(Collectors.toList());
    }

    private PlaceDTO mapPlaceToDTO(Place place){
        return new PlaceDTO(place.getId(), place.getName());
    }
}
