package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.property.PropertyListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListing;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingCommand;
import io.github.ksamodol.oglasnikbackend.entity.listing.vehicle.VehicleListingDTO;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.repository.*;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.hibernate.mapping.Property;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private ListingRepository listingRepository;
    private PropertyListingRepository propertyListingRepository;
    private VehicleListingRepository vehicleListingRepository;
    private PlaceRepository placeRepository;

    private FileStorageService fileStorageService;

    public ListingServiceImpl(
            ListingRepository listingRepository,
            PropertyListingRepository propertyListingRepository,
            VehicleListingRepository vehicleListingRepository,
            PlaceRepository placeRepository,
            @Lazy FileStorageService fileStorageService
    ){
        this.listingRepository = listingRepository;
        this.propertyListingRepository = propertyListingRepository;
        this.vehicleListingRepository = vehicleListingRepository;
        this.placeRepository = placeRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public <T extends ListingDTO> Optional<T> findListingById(Long id) {
        if (!listingRepository.existsById(id)){
            return Optional.empty();
        }
        Category category = listingRepository.findById(id).get().getCategory();
        if(category == Category.VEHICLE){
            return (Optional<T>) vehicleListingRepository.findById(id).map(this::mapVehicleListingToDTO);
        }else if(category == Category.PROPERTY){
            return (Optional<T>) propertyListingRepository.findById(id).map(this::mapPropertyListingToDTO);
        }else{
            return (Optional<T>) listingRepository.findById(id).map(this::mapListingToDTO);
        }
    }

    @Override
    public List<ListingDTO> findAllListings(Specification<Listing> specification, int page, int size) {
        return listingRepository.findAll(
                specification,
                PageRequest.of(page, size)
        ).stream().map(this::mapListingToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ListingDTO> findAllListingsByUser(User user){
        return listingRepository.findAllByUser(user).stream().map(this::mapListingToDTO).collect(Collectors.toList());
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
    public Optional<ListingDTO> saveListing(ListingCommand listingCommand, MultipartFile[] images, User user) {
        if (!placeRepository.existsById(listingCommand.getPlaceId())) {
            throw new IllegalArgumentException("Bad place id!");
        }
        Optional<ListingDTO> listingDTO = Optional.of(mapListingToDTO(listingRepository.save(mapListingCommandToListing(listingCommand, user))));

        fileStorageService.storeFiles(images, listingDTO.get().getId());

        return listingDTO;
    }

    @Override
    public Optional<VehicleListingDTO> saveVehicleListing(
            VehicleListingCommand vehicleListingCommand,
            MultipartFile[] images,
            User user
    ){
        if(!placeRepository.existsById(vehicleListingCommand.getPlaceId())){
            throw new IllegalArgumentException("Bad place id!");
        }

        Optional<VehicleListingDTO> vehicleListingDTO = Optional.of(mapVehicleListingToDTO(listingRepository.save(mapVehicleCommandToVehicleListing(vehicleListingCommand, user))));

        fileStorageService.storeFiles(images, vehicleListingDTO.get().getId());

        return vehicleListingDTO;
    }

    @Override
    public Optional<PropertyListingDTO> savePropertyListing(
            PropertyListingCommand propertyListingCommand,
            MultipartFile[] images,
            User user
    ){
        if(!placeRepository.existsById(propertyListingCommand.getPlaceId())){
            throw new IllegalArgumentException("Bad place id!");
        }


        Optional<PropertyListingDTO> propertyListingDTO = Optional.of(mapPropertyListingToDTO(listingRepository.save(mapPropertyCommandToPropertyListing(propertyListingCommand, user))));

        fileStorageService.storeFiles(images, propertyListingDTO.get().getId());

        return propertyListingDTO;
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
        List<String> images = fileStorageService.getImageNames(listing.getId());
        return new ListingDTO(listing, images);
    }
    private PropertyListingDTO mapPropertyListingToDTO(PropertyListing propertyListing){
        List<String> images = fileStorageService.getImageNames(propertyListing.getId());
        return new PropertyListingDTO(propertyListing, images);
    }
    private VehicleListingDTO mapVehicleListingToDTO(VehicleListing vehicleListing){
        List<String> images = fileStorageService.getImageNames(vehicleListing.getId());
        return new VehicleListingDTO(vehicleListing, images);
    }
    private Listing mapListingCommandToListing(ListingCommand listingCommand, User user) throws IllegalArgumentException{
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

    private VehicleListing mapVehicleCommandToVehicleListing(VehicleListingCommand vehicleListingCommand, User user) throws IllegalArgumentException{
        Optional<Place> place = placeRepository.findById(vehicleListingCommand.getPlaceId());
        VehicleListing vehicleListing = new VehicleListing();
        vehicleListing.setTitle(vehicleListingCommand.getTitle());
        vehicleListing.setDescription(vehicleListingCommand.getDescription());
        vehicleListing.setCategory(vehicleListingCommand.getCategory());
        vehicleListing.setCondition(vehicleListingCommand.getCondition());
        vehicleListing.setPrice(vehicleListingCommand.getPrice());
        vehicleListing.setTimestampCreated(Instant.now());
        vehicleListing.setPlace(place.get());
        vehicleListing.setUser(user); //TODO: Implement real user
        vehicleListing.setMake(vehicleListingCommand.getMake());
        vehicleListing.setModel(vehicleListingCommand.getModel());
        vehicleListing.setYear(vehicleListingCommand.getYear());
        vehicleListing.setMileage(vehicleListingCommand.getMileage());
        vehicleListing.setHorsepower(vehicleListingCommand.getHorsepower());
        return vehicleListing;
    }
    private PropertyListing mapPropertyCommandToPropertyListing(PropertyListingCommand propertyListingCommand, User user) throws IllegalArgumentException{
        Optional<Place> place = placeRepository.findById(propertyListingCommand.getPlaceId());
        PropertyListing propertyListing = new PropertyListing();
        propertyListing.setTitle(propertyListingCommand.getTitle());
        propertyListing.setDescription(propertyListingCommand.getDescription());
        propertyListing.setCategory(propertyListingCommand.getCategory());
        propertyListing.setCondition(propertyListingCommand.getCondition());
        propertyListing.setPrice(propertyListingCommand.getPrice());
        propertyListing.setTimestampCreated(Instant.now());
        propertyListing.setPlace(place.get());
        propertyListing.setUser(user); //TODO: Implement real user
        propertyListing.setInsideArea(propertyListingCommand.getInsideArea());
        propertyListing.setOutsideArea(propertyListingCommand.getOutsideArea());
        propertyListing.setYearBuilt(propertyListingCommand.getYearBuilt());
        propertyListing.setFloors(propertyListingCommand.getFloors());
        propertyListing.setNumberOfRooms(propertyListingCommand.getNumberOfRooms());
        return propertyListing;
    }


    private boolean isValid(ListingCommand listingCommand){//TODO: implement
        return true;
    }
}
