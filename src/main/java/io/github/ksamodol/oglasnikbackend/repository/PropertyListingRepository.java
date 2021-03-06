package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyListingRepository extends JpaRepository<PropertyListing, Long> {
}
