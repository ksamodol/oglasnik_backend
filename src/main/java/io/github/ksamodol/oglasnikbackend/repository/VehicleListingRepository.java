package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleListingRepository extends JpaRepository<VehicleListing, Long>, JpaSpecificationExecutor<VehicleListing> {
}
