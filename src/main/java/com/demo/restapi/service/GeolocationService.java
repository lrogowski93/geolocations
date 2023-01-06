package com.demo.restapi.service;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction;

@Service
@RequiredArgsConstructor
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public Geolocation addGeolocation(GeolocationRequest geolocation) {
        Geolocation newGeolocation = new Geolocation();
        newGeolocation.setDeviceId(geolocation.getDeviceId());
        newGeolocation.setLatitude(geolocation.getLatitude());
        newGeolocation.setLongitude(geolocation.getLongitude());
        newGeolocation.setCreated(geolocation.getCreated());

        return geolocationRepository.save(newGeolocation);
    }

    private GeolocationResponse mapToGeolocationResponse(Geolocation geolocation) {
        return GeolocationResponse.builder()
                .id(geolocation.getId())
                .deviceId(geolocation.getDeviceId())
                .latitude(geolocation.getLatitude())
                .longitude(geolocation.getLongitude())
                .created(geolocation.getCreated())
                .build();

    }

    private List<GeolocationResponse> mapToGeolocationResponses(List<Geolocation> geolocations) {
        return geolocations.stream()
                .map(geolocation -> mapToGeolocationResponse(geolocation))
                .collect(Collectors.toList());

    }

    public List<GeolocationResponse> getGeolocations(int page, Direction sort) {
        int pageNumber = Math.max(page, 0);
        Direction sortDirection = sort != null ? sort : Direction.ASC;
        List<Geolocation> geolocations = geolocationRepository.findAllGeolocations(PageRequest.of(pageNumber, 50, Sort.by(sortDirection, "id")));

        return mapToGeolocationResponses(geolocations);
    }
}
