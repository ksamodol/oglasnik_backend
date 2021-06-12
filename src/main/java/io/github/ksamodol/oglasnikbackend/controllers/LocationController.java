package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.location.CountyDTO;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.entity.location.PlaceDTO;
import io.github.ksamodol.oglasnikbackend.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/place")
    public List<PlaceDTO> findAllPlaces(){
        return locationService.findAllPlaces();
    }

    @GetMapping(path = "/place", params = "countyId")
    public List<PlaceDTO> findAllPlacesByCountyId(@RequestParam Long countyId){
           return locationService.findAllPlacesByCountyId(countyId);
    }
    @GetMapping(path = "/county")
    public List<CountyDTO> findAllCounties(){
        return locationService.findAllCounties();
    }


}
