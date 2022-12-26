package com.demo.restapi.service;

import com.demo.restapi.controller.dto.GeolocationRequestDto;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.repository.GeolocationRepository;
import org.springframework.stereotype.Service;

@Service
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public GeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    public Geolocation addGeolocation(GeolocationRequestDto geolocation) {
        Geolocation newGeolocation = new Geolocation();
        newGeolocation.setDeviceId(geolocation.getDeviceId());
        newGeolocation.setLatitude(geolocation.getLatitude());
        newGeolocation.setLongitude(geolocation.getLongitude());
        newGeolocation.setCreated(geolocation.getCreated());

        return geolocationRepository.save(newGeolocation);
    }




}
