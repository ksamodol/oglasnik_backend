package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<ListingDTO> findAllListings(Specification<Listing> specification, int page, int size);
    <T extends ListingDTO> Optional<T> findListingById(Long id);
    List<PropertyListingDTO> findAllPropertyListings(int page, int size);
    List<VehicleListingDTO> findAllVehicleListings(Specification<VehicleListing> specification, int page, int size);
    List<ListingDTO> findAllListingsByUser(User user);
    Optional<ListingDTO> saveListing(ListingCommand listingCommand, MultipartFile[] images, User user);
    Optional<VehicleListingDTO> saveVehicleListing(VehicleListingCommand vehicleListingCommand, MultipartFile[] images, User user);
    Optional<PropertyListingDTO> savePropertyListing(PropertyListingCommand propertyListingCommand, MultipartFile[] images, User user);
    boolean delete(Long listingId);
}
