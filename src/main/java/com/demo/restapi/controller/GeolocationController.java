package com.demo.restapi.controller;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.model.Geolocation;
import com.demo.restapi.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeolocationController {

    private final GeolocationService geolocationService;


    @GetMapping("/geolocations")
    public List<GeolocationResponse> getGeolocations(@RequestParam(defaultValue = "0") int page, Sort.Direction sort) {
        return geolocationService.getGeolocations(page, sort);
    }

    @PostMapping("/geolocations")
    public Geolocation addGeolocation(@RequestBody @Valid GeolocationRequest geolocation) {
        return geolocationService.addGeolocation(geolocation);
    }


}
