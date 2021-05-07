package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<ListingDTO> findAllListings(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size) {
        return listingService.findAllListings(page, size);
    }
    @GetMapping("/property")
    public List<PropertyListingDTO> findAllPropertyListings(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size) {
        return listingService.findAllPropertyListings(page, size);
    }
    @GetMapping("/vehicle")
    public List<VehicleListingDTO> findAllVehicleListings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return listingService.findAllVehicleListings(page, size);
    }
    @GetMapping
    public List<ListingDTO> findAllListingsByCategory(@RequestParam String category){
        return null;
    }


    /*@GetMapping("/{id}")
    public ResponseEntity<Listing> findListingById(@PathVariable Long id){
        return listingService.findListingById(id)
                .map(
                        listingDTO -> ResponseEntity
                            .status(HttpStatus.OK)
                            .body(listingDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
    }*/
}
