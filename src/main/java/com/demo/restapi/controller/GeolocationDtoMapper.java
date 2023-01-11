package com.demo.restapi.controller;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.model.Geolocation;

import java.util.List;
import java.util.stream.Collectors;

public class GeolocationDtoMapper {
    public static GeolocationResponse mapToGeolocationResponse(Geolocation geolocation) {
        return GeolocationResponse.builder()
                .id(geolocation.getId())
                .deviceId(geolocation.getDeviceId())
                .latitude(geolocation.getLatitude())
                .longitude(geolocation.getLongitude())
                .created(geolocation.getCreated())
                .build();
    }

    public static Geolocation mapToGeolocation(GeolocationRequest geolocationRequest) {
        Geolocation geolocation = new Geolocation();

        geolocation.setDeviceId(geolocationRequest.getDeviceId());
        geolocation.setLatitude(geolocationRequest.getLatitude());
        geolocation.setLongitude(geolocationRequest.getLongitude());
        geolocation.setCreated(geolocationRequest.getCreated());

        return geolocation;
    }

    public static List<GeolocationResponse> mapToGeolocationResponses(List<Geolocation> geolocations) {
        return geolocations.stream()
                .map(geolocation -> mapToGeolocationResponse(geolocation))
                .collect(Collectors.toList());
    }
}
