package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
    public List<VehicleListingDTO> findAllVehicleListings(
            @And({
                    @Spec(path="title", params="search", spec=Like.class),
                    @Spec(path="description", params="search", spec=Like.class),
                    @Spec(path="condition", params="condition", spec=EqualIgnoreCase.class),
                    @Spec(path="price", params="priceMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="price", params="priceMax", spec=LessThanOrEqual.class),
                    @Spec(path="horsepower", params="horsepowerMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="horsepower", params="horsepowerMax", spec=LessThanOrEqual.class),
                    @Spec(path="make", params="make", spec=Like.class),
                    @Spec(path="model", params="model", spec=Like.class),
                    @Spec(path="mileage", params="mileageMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="horsepower", params="mileageMax", spec=LessThanOrEqual.class),
                    @Spec(path="transmission", params="transmission", spec=EqualIgnoreCase.class)
            }) Specification<VehicleListing> specification,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return listingService.findAllVehicleListings(specification, page, size);
    }
}
