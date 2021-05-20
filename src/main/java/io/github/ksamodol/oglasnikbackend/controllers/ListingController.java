package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<ListingDTO> findAllListings(
            @And({
                    @Spec(path="title", params="search", spec=Like.class),
                    @Spec(path="price", params="priceMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="price", params="priceMax", spec=LessThanOrEqual.class)
            })
                    Specification<Listing> specification,
            @RequestParam(defaultValue = "0")@PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(20) int size
    ){
        return listingService.findAllListings(specification, page, size);
    }

    @GetMapping("/{category}")
    public List<ListingDTO> findAllListingsByCategory(@PathVariable Category category){ //TODO: ERROR HANDLING
        return listingService.findAllListingsByCategory(category);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> badCategoryHandler(){
        return ResponseEntity.badRequest().body("No such category found!");
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
