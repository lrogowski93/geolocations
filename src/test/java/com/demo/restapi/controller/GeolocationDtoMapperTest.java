package com.demo.restapi.controller;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.model.Geolocation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.demo.restapi.controller.GeolocationDtoMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GeolocationDtoMapperTest {

    @Test
    void shouldGetGeolocationResponse() {
        //given
        Geolocation geolocation = new Geolocation();
        geolocation.setId(111L);
        geolocation.setLongitude(12.5);
        geolocation.setLatitude(50.3);
        //when
        GeolocationResponse geolocationResponse = mapToGeolocationResponse(geolocation);
        //then
        assertEquals(geolocation.getId(), geolocationResponse.getId());
        assertEquals(geolocation.getLatitude(), geolocationResponse.getLatitude());
        assertEquals(geolocation.getLongitude(), geolocationResponse.getLongitude());
    }

    @Test
    void shouldGetGeolocation() {
        //given
        GeolocationRequest geolocationRequest = new GeolocationRequest();
        geolocationRequest.setLongitude(12.5);
        geolocationRequest.setLatitude(50.3);
        //when
        Geolocation geolocation = mapToGeolocation(geolocationRequest);
        //then
        assertEquals(geolocation.getLatitude(), geolocationRequest.getLatitude());
        assertEquals(geolocation.getLongitude(), geolocationRequest.getLongitude());
    }

    @Test
    void shouldGetGeolocationResponses() {
        //given
        Geolocation geolocation = new Geolocation();
        geolocation.setId(111L);
        geolocation.setLongitude(12.5);
        geolocation.setLatitude(50.3);
        List<Geolocation> geolocations = new ArrayList<>();
        geolocations.add(geolocation);
        //when
        List<GeolocationResponse> geolocationResponses = mapToGeolocationResponses(geolocations);
        //then
        assertEquals(geolocations.get(0).getId(), geolocationResponses.get(0).getId());
        assertEquals(geolocations.get(0).getLatitude(), geolocationResponses.get(0).getLatitude());
        assertEquals(geolocations.get(0).getLongitude(), geolocationResponses.get(0).getLongitude());
    }
}