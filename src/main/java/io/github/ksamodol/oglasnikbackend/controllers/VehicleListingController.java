package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listing/vehicle")
public class VehicleListingController {
    private ListingService listingService;

    public VehicleListingController(ListingService listingService) {
        this.listingService = listingService;
    }
    @GetMapping
    public List<VehicleListingDTO> findAllVehicleListings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return listingService.findAllVehicleListings(page, size);
    }
}
