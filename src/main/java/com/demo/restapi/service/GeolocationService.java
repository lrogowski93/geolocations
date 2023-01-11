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

import static com.demo.restapi.controller.GeolocationDtoMapper.*;
import static org.springframework.data.domain.Sort.Direction;

@Service
@RequiredArgsConstructor
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public GeolocationResponse addGeolocation(GeolocationRequest geolocationRequest) {

        Geolocation newGeolocation = geolocationRepository.save(
                mapToGeolocation(geolocationRequest)
        );

        return mapToGeolocationResponse(newGeolocation);
    }


    public List<GeolocationResponse> getGeolocations(int page, Direction sortDirection) {
        int pageNumber = Math.max(page, 0);
        List<Geolocation> geolocations = geolocationRepository.findAllGeolocations(PageRequest.of(pageNumber, 50, Sort.by(sortDirection, "id")));
        return mapToGeolocationResponses(geolocations);
    }

    public GeolocationResponse getGeolocation(long id) {
        Geolocation geolocation = geolocationRepository.findById(id).orElseThrow(() -> new RuntimeException("Geolocation with id:" + id + " not found"));

        return mapToGeolocationResponse(geolocation);
    }

    public List<GeolocationResponse> getGeolocationsWithDeviceId(long deviceId) {
        List<Geolocation> geolocations = geolocationRepository.findByDeviceId(deviceId);
        return mapToGeolocationResponses(geolocations);
    }
}
