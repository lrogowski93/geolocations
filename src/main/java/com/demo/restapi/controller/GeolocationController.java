package com.demo.restapi.controller;

import com.demo.restapi.controller.dto.GeolocationRequestDto;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.service.GeolocationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class GeolocationController {

    private final GeolocationService geolocationService;

    public GeolocationController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }


    @PostMapping("/geolocations")
    public Geolocation addGeolocation(@RequestBody @Valid GeolocationRequestDto geolocation){
        return geolocationService.addGeolocation(geolocation);
    }


}
