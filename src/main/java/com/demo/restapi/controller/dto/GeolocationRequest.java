package com.demo.restapi.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
public class GeolocationRequest {

    private long deviceId;
    @Min(value = -90, message = "Latitude value should be between -90 and 90")
    @Max(value = 90, message = "Latitude value should be between -90 and 90")
    private double latitude;
    @Min(value = -180, message = "Longitude value should be between -180 and 180")
    @Max(value = 180, message = "Longitude value should be between -180 and 180")
    private double longitude;
    private LocalDateTime created;

}
