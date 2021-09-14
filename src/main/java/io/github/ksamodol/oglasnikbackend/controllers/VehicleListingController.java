package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.security.User;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
                    @Spec(path="mileage", params="mileageMax", spec=LessThanOrEqual.class),
                    @Spec(path="transmission", params="transmission", spec=EqualIgnoreCase.class)
            }) Specification<VehicleListing> specification,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return listingService.findAllVehicleListings(specification, page, size);
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ResponseEntity<VehicleListingDTO> saveVehicle(
            @Valid @RequestPart("listing") VehicleListingCommand vehicleListingCommand,
            @RequestPart("images") MultipartFile[] images,
            Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return listingService.saveVehicleListing(vehicleListingCommand, images, user).map(
                vehicleListingDTO -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(vehicleListingDTO)
        )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }
}
