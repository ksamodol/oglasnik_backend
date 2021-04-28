package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.entity.location.PlaceDTO;
import io.github.ksamodol.oglasnikbackend.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("place")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @GetMapping
    public List<PlaceDTO> findAllPlaces(){
        return locationService.findAllPlaces();
    }
    @GetMapping(params = "countyId")
    public List<PlaceDTO> findAllPlacesByCountyId(@RequestParam int countyId){
           return locationService.findAllPlacesByCountyId(countyId);
    }
}
