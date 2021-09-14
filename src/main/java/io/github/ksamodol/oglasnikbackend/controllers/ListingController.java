package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.security.User;
import io.github.ksamodol.oglasnikbackend.services.ListingService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/listing")
public class ListingController {

    private ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("{listingId}")
    public <T extends ListingDTO> T findListingById(@PathVariable Long listingId){
        return (T) listingService.findListingById(listingId).get();
    }

    @GetMapping
    public List<ListingDTO> findAllListings(
            @Join(path="place", alias="p")
            @Join(path="p.county", alias="c")
            @And({
                    @Spec(path="title", params="search", spec=Like.class),
                    @Spec(path="condition", params="condition", spec=EqualIgnoreCase.class),
                    @Spec(path="category", params="category", spec=Equal.class),
                    @Spec(path="c.id", params="county", spec=Equal.class),
                    @Spec(path="p.id", params="place", spec=Equal.class),
                    @Spec(path="price", params="priceMin", spec=GreaterThanOrEqual.class),
                    @Spec(path="price", params="priceMax", spec=LessThanOrEqual.class)
            }) Specification<Listing> specification,
            @RequestParam(defaultValue = "0")@PositiveOrZero int page,
            @RequestParam(defaultValue = "10")@PositiveOrZero @Max(20) int size
    ){
        return listingService.findAllListings(specification, page, size);
    }

    @GetMapping("/personal")
    @Secured("ROLE_USER")
    public List<ListingDTO> findAllListingsByUser(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return listingService.findAllListingsByUser(user);
    }

    @PostMapping
    @Secured("ROLE_USER")
    public ResponseEntity<ListingDTO> saveListing(
            @Valid @RequestPart("listing") ListingCommand listingCommand,
            @RequestPart("images") MultipartFile[] images,
            Authentication authentication
    ){
        User user = (User) authentication.getPrincipal();
        return listingService.saveListing(listingCommand, images, user).map(
                listingDTO -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(listingDTO)
        )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }


    @DeleteMapping("/{listingId}")
    public ResponseEntity<Void> delete(@PathVariable Long listingId){
        if(listingService.delete(listingId)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
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
