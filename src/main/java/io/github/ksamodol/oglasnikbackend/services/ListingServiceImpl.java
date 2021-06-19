package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.repository.*;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private ListingRepository listingRepository;
    private PropertyListingRepository propertyListingRepository;
    private VehicleListingRepository vehicleListingRepository;
    private PlaceRepository placeRepository;

    public ListingServiceImpl(
            ListingRepository listingRepository,
            PropertyListingRepository propertyListingRepository,
            VehicleListingRepository vehicleListingRepository,
            PlaceRepository placeRepository
    ){
        this.listingRepository = listingRepository;
        this.propertyListingRepository = propertyListingRepository;
        this.vehicleListingRepository = vehicleListingRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public Optional<ListingDTO> findListingById(Long id) {
        return listingRepository.findById(id).map(this::mapListingToDTO);
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

    @Override
    public Optional<ListingDTO> save(ListingCommand listingCommand, User user) {
        if(!placeRepository.existsById(listingCommand.getPlaceId())){
            throw new IllegalArgumentException("Bad place id!");
        }
        return Optional.of(mapListingToDTO(listingRepository.save(mapCommandToListing(listingCommand, user))));
    }


    @Override
    public boolean delete(Long listingId) {
        if(!listingRepository.existsById(listingId)){
            return false;
        }
        listingRepository.deleteById(listingId);
        return true;
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
    private Listing mapCommandToListing(ListingCommand listingCommand, User user) throws IllegalArgumentException{
        Optional<Place> place = placeRepository.findById(listingCommand.getPlaceId());
        Listing listing = new Listing();
        listing.setTitle(listingCommand.getTitle());
        listing.setDescription(listingCommand.getDescription());
        listing.setCategory(listingCommand.getCategory());
        listing.setCondition(listingCommand.getCondition());
        listing.setPrice(listingCommand.getPrice());
        listing.setTimestampCreated(Instant.now());
        listing.setPlace(place.get());
        listing.setUser(user); //TODO: Implement real user

        return listing;
    }

    private boolean isValid(ListingCommand listingCommand){//TODO: implement
        return true;
    }
}
