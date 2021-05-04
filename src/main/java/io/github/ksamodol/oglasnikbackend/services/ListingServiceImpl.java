package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;
import io.github.ksamodol.oglasnikbackend.repository.ListingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public List<ListingDTO> findAllListings(int page, int size) {
        return listingRepository.findAll(PageRequest.of(page, size)).stream().map(this::mapListingToDTO).collect(Collectors.toList());
    }

    private ListingDTO mapListingToDTO(Listing listing){
        return new ListingDTO(
                listing.getId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getCondition().name(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC)).format(listing.getTimestampCreated()),
                listing.getCategory().name(),
                listing.getPlace().getName(),
                listing.getUser().getUsername()
        );
    }
}
