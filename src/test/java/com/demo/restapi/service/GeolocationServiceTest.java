package com.demo.restapi.service;

import com.demo.restapi.controller.dto.GeolocationRequestDto;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.repository.GeolocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Geolocation newGeolocation = geolocationService.addGeolocation(new GeolocationRequestDto());
        //then
        assertThat(newGeolocation).isEqualTo(expectedGeolocation);


    }

}