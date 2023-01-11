package com.demo.restapi.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class GeolocationResponse {

    private long id;
    private long deviceId;
    private double latitude;
    private double longitude;
    private LocalDateTime created;
}
