package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.location.County;
import io.github.ksamodol.oglasnikbackend.entity.location.CountyDTO;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.entity.location.PlaceDTO;
import io.github.ksamodol.oglasnikbackend.repository.CountyRepository;
import io.github.ksamodol.oglasnikbackend.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final PlaceRepository placeRepository;
    private final CountyRepository countyRepository;

    public LocationServiceImpl(PlaceRepository placeRepository, CountyRepository countyRepository) {
        this.placeRepository = placeRepository;
        this.countyRepository = countyRepository;
    }

    @Override
    public List<PlaceDTO> findAllPlaces() {
        return placeRepository.findAll().stream().map(this::mapPlaceToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PlaceDTO> findAllPlacesByCountyId(int id){
        return placeRepository.findAllByCounty_id(id).stream().map(this::mapPlaceToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CountyDTO> findAllCounties() {
        return countyRepository.findAll().stream().map(this::mapCountyToDTO).collect(Collectors.toList());
    }

    private PlaceDTO mapPlaceToDTO(Place place){
        return new PlaceDTO(place.getId(), place.getName());
    }
    private CountyDTO mapCountyToDTO(County county){
        return new CountyDTO(county.getId(), county.getName());
    }
}
