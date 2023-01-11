package com.demo.restapi.service;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.repository.GeolocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeolocationServiceTest {

    private GeolocationService geolocationService;
    private GeolocationRepository geolocationRepository;

    @BeforeEach
    void setupService() {
        geolocationRepository = mock(GeolocationRepository.class);
        geolocationService = new GeolocationService(geolocationRepository);
    }

    @Test
    void shouldAddGeolocation() {
        //given
        Geolocation expectedGeolocation = new Geolocation();
        expectedGeolocation.setId(123L);
        when(geolocationRepository.save(any(Geolocation.class))).thenReturn(expectedGeolocation);
        //when
        GeolocationResponse newGeolocation = geolocationService.addGeolocation(new GeolocationRequest());
        //then
        assertThat(newGeolocation.getId()).isEqualTo(expectedGeolocation.getId());
    }

    @Test
    void shouldGetGeolocations() {
        //given
        Geolocation geolocation = new Geolocation();
        geolocation.setId(123L);
        List<Geolocation> geolocations = new ArrayList<>();
        geolocations.add(geolocation);
        when(geolocationRepository.findAllGeolocations(any(PageRequest.class))).thenReturn(geolocations);
        //when
        List<GeolocationResponse> expectedGeolocationResponse = geolocationService.getGeolocations(1, Sort.Direction.ASC);
        //then
        assertThat(expectedGeolocationResponse.get(0).getId()).isEqualTo(geolocations.get(0).getId());
    }

    @Test
    void shouldGetGeolocation() {
        //given
        Geolocation geolocation = new Geolocation();
        geolocation.setId(123L);
        when(geolocationRepository.findById(any(long.class))).thenReturn(Optional.of(geolocation));
        //when
        GeolocationResponse expectedGeolocationResponse = geolocationService.getGeolocation(1L);
        //then
        assertThat(expectedGeolocationResponse.getId()).isEqualTo(geolocation.getId());
    }

    @Test
    void shouldGetGeolocationsWithDeviceId() {
        //given
        Geolocation geolocation = new Geolocation();
        geolocation.setId(1123L);
        geolocation.setDeviceId(1L);
        List<Geolocation> geolocations = new ArrayList<>();
        geolocations.add(geolocation);
        when(geolocationRepository.findByDeviceId(any(long.class))).thenReturn(geolocations);
        //when
        List<GeolocationResponse> expectedGeolocationResponses = geolocationService.getGeolocationsWithDeviceId(1L);
        //then
        assertThat(expectedGeolocationResponses.get(0).getId()).isEqualTo(geolocations.get(0).getId());
    }

}