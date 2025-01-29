package org.example.repository;

import org.example.model.PetListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetListingRepository extends JpaRepository<PetListing, Long> {
}
