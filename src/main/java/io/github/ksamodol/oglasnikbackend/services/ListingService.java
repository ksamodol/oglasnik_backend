package io.github.ksamodol.oglasnikbackend.services;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;

import java.util.List;

public interface ListingService {
    List<ListingDTO> findAllListings(int page, int size);
}
