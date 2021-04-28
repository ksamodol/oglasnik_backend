package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;

import java.util.List;

public interface PropertyListingRepository {
    List<PropertyListing> getAll();
}
