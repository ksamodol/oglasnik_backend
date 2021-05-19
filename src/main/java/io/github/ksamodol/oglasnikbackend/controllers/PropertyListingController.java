package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listing/property")
public class PropertyListingController {
    private ListingService listingService;

    public PropertyListingController(ListingService listingService) {
        this.listingService = listingService;
    }
    @GetMapping
    public List<PropertyListingDTO> findAllPropertyListings(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size) {
        return listingService.findAllPropertyListings(page, size);
    }

}
