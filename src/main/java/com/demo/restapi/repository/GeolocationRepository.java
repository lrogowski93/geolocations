package com.demo.restapi.repository;

import com.demo.restapi.model.Geolocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
    @Query("Select g From Geolocation g")
    List<Geolocation> findAllGeolocations(Pageable page);
}
