package com.demo.restapi.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class GeolocationRequestDto {

    private long deviceId;
    @Min(value=-90, message = "Latitude value should be between -90 and 90")
    @Max(value=90, message = "Latitude value should be between -90 and 90")
    private double latitude;
    @Min(value=-180, message = "Longitude value should be between -180 and 180")
    @Max(value=180, message = "Longitude value should be between -180 and 180")
    private double longitude;

    private LocalDateTime created;

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
