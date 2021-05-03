package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

}
