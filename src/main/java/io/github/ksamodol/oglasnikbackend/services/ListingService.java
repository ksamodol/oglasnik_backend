package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<ListingDTO> findAllListings(Specification<Listing> specification, int page, int size);
    List<PropertyListingDTO> findAllPropertyListings(int page, int size);
    List<VehicleListingDTO> findAllVehicleListings(Specification<VehicleListing> specification, int page, int size);
    List<ListingDTO> findAllListingsByCategory(Category category);
    Optional<ListingDTO> save(ListingCommand listingCommand);
}
