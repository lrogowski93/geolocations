package com.demo.restapi.controller;

import com.demo.restapi.controller.dto.GeolocationRequest;
import com.demo.restapi.controller.dto.GeolocationResponse;
import com.demo.restapi.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeolocationController {

    private final GeolocationService geolocationService;


    @GetMapping("/geolocations")
    public List<GeolocationResponse> getGeolocations(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "ASC") Direction sortDirection) {
        return geolocationService.getGeolocations(page, sortDirection);
    }

    @GetMapping("/geolocation/{id}")
    public GeolocationResponse getGeolocation(@PathVariable long id) {
        return geolocationService.getGeolocation(id);
    }

    @GetMapping("/geolocations/device/{deviceId}")
    public List<GeolocationResponse> getGeolocationsWithDeviceId(@PathVariable long deviceId) {
        return geolocationService.getGeolocationsWithDeviceId(deviceId);
    }


    @PostMapping("/geolocations")
    public GeolocationResponse addGeolocation(@RequestBody @Valid GeolocationRequest geolocationRequest) {
        return geolocationService.addGeolocation(geolocationRequest);
    }


}
