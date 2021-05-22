package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
    public List<PropertyListingDTO> findAllPropertyListings(
            @And({
                    @Spec(path="title", params="search", spec= Like.class),
                    @Spec(path="condition", params="condition", spec= EqualIgnoreCase.class),
                    @Spec(path="price", params="priceMin", spec= GreaterThanOrEqual.class),
                    @Spec(path="price", params="priceMax", spec= LessThanOrEqual.class),
                    @Spec(path="insideArea", params="insideAreaMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="insideArea", params="insideAreaMax", spec=LessThanOrEqual.class),
                    @Spec(path="outsideArea", params="outsideAreaMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="outsideArea", params="outsideAreaMax", spec=LessThanOrEqual.class),
                    @Spec(path="floors", params="floorMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="floors", params="floorMax", spec=LessThanOrEqual.class),
                    @Spec(path="yearBuilt", params="yearBuiltMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="yearBuilt", params="yearBuiltMax", spec=LessThanOrEqual.class),
                    @Spec(path="numberOfRooms", params="numberOfRoomsMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="numberOfRooms", params="numberOfRoomsMax", spec=LessThanOrEqual.class),
                    @Spec(path="propertyType", params="propertyType", spec=EqualIgnoreCase.class)
            }) Specification<PropertyListing> specification,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return listingService.findAllPropertyListings(page, size);
    }

}
