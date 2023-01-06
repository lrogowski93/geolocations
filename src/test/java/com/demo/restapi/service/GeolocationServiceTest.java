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
        when(geolocationRepository.save(any(Geolocation.class))).thenReturn(expectedGeolocation);
        //when
        Geolocation newGeolocation = geolocationService.addGeolocation(new GeolocationRequest());
        //then
        assertThat(newGeolocation).isEqualTo(expectedGeolocation);


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

}