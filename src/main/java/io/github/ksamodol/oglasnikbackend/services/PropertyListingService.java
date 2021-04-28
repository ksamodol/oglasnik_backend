package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyListingService {
    public List<PropertyListingDTO> getAllPropertyListings();
}
