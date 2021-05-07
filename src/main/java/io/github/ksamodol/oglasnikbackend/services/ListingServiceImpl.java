package io.github.ksamodol.oglasnikbackend.services;

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
    public List<ListingDTO> findAllListings(int page, int size) {
        return listingRepository.findAll(PageRequest.of(page, size)).stream().map(this::mapListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PropertyListingDTO> findAllPropertyListings(int page, int size){
        return propertyListingRepository.findAll(PageRequest.of(page, size)).stream().map(this::mapPropertyListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<VehicleListingDTO> findAllVehicleListings(int page, int size) {
        return vehicleListingRepository.findAll(PageRequest.of(page, size)).stream().map(this::mapVehicleListingToDTO).collect(Collectors.toList());
    }


    private ListingDTO mapListingToDTO(Listing listing){  //TODO: preselit u klasu, i da svaka klasa definira svoju map metodu?
        return new ListingDTO(
                listing.getId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getCondition().name(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC)).format(listing.getTimestampCreated()), //TODO: zone?
                listing.getCategory().name(),
                listing.getPlace().getName(),
                listing.getUser().getUsername()
        );
    }
    private PropertyListingDTO mapPropertyListingToDTO(PropertyListing propertyListing){
        return new PropertyListingDTO(
                propertyListing.getId(),
                propertyListing.getTitle(),
                propertyListing.getDescription(),
                propertyListing.getCondition().name(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC)).format(propertyListing.getTimestampCreated()),
                propertyListing.getCategory().name(),
                propertyListing.getPlace().getName(),
                propertyListing.getUser().getUsername(),
                propertyListing.getInsideArea(),
                propertyListing.getOutsideArea(),
                propertyListing.getFloors(),
                propertyListing.getYearBuilt(),
                propertyListing.getNumberOfRooms(),
                propertyListing.getPropertyType().name()
        );
    }
    private VehicleListingDTO mapVehicleListingToDTO(VehicleListing vehicleListing){
        return new VehicleListingDTO(
            vehicleListing.getId(),
                vehicleListing.getTitle(),
                vehicleListing.getDescription(),
                vehicleListing.getCondition().name(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC)).format(vehicleListing.getTimestampCreated()),
                vehicleListing.getCategory().name(),
                vehicleListing.getPlace().getName(),
                vehicleListing.getUser().getUsername(),
                vehicleListing.getMake(),
                vehicleListing.getModel(),
                vehicleListing.getYear(),
                vehicleListing.getMileage(),
                vehicleListing.getHorsepower(),
                vehicleListing.getTransmission().name()
        );
    }
}
