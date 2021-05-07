package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<ListingDTO> findAllListings(int page, int size);
    List<PropertyListingDTO> findAllPropertyListings(int page, int size);
    List<VehicleListingDTO> findAllVehicleListings(int page, int size);
    List<ListingDTO> findAllListingsByCategory(String category);
}
