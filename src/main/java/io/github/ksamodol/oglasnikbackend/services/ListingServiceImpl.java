package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.repository.ListingRepository;
import io.github.ksamodol.oglasnikbackend.repository.PropertyListingRepository;
import io.github.ksamodol.oglasnikbackend.repository.VehicleListingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private ListingRepository listingRepository;
    private PropertyListingRepository propertyListingRepository;
    private VehicleListingRepository vehicleListingRepository;

    public ListingServiceImpl(ListingRepository listingRepository, PropertyListingRepository propertyListingRepository, VehicleListingRepository vehicleListingRepository) {
        this.listingRepository = listingRepository;
        this.propertyListingRepository = propertyListingRepository;
        this.vehicleListingRepository = vehicleListingRepository;
    }

    @Override
    public List<ListingDTO> findAllListings(Specification<Listing> specification, int page, int size) {
        return listingRepository.findAll(specification, PageRequest.of(page, size)).stream().map(this::mapListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PropertyListingDTO> findAllPropertyListings(int page, int size){
        return propertyListingRepository.findAll(PageRequest.of(page, size)).stream().map(this::mapPropertyListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VehicleListingDTO> findAllVehicleListings(Specification<VehicleListing> specification, int page, int size) {
        return vehicleListingRepository.findAll(specification, PageRequest.of(page, size)).stream().map(this::mapVehicleListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ListingDTO> findAllListingsByCategory(Category category) {
        return listingRepository.findAllByCategory(category).stream().map(this::mapListingToDTO).collect(Collectors.toList());
    }

    private ListingDTO mapListingToDTO(Listing listing){
        return new ListingDTO(listing);
    }
    private PropertyListingDTO mapPropertyListingToDTO(PropertyListing propertyListing){
        return new PropertyListingDTO(propertyListing);
    }
    private VehicleListingDTO mapVehicleListingToDTO(VehicleListing vehicleListing){
        return new VehicleListingDTO(vehicleListing);
    }
}
