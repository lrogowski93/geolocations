package com.demo.restapi.controller.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class GeolocationRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    private Set<ConstraintViolation<GeolocationRequest>> getConstraintViolations(GeolocationRequest geolocationRequest) {
        return validator.validate(geolocationRequest);
    }


    @Test
    void shouldThrowExceptionIfNotValidGeolocation() {
        //given
        GeolocationRequest geolocationRequest = new GeolocationRequest();
        geolocationRequest.setLatitude(100);
        geolocationRequest.setLongitude(-500);
        //then
        assertThat(
                getConstraintViolations(geolocationRequest).size()
        ).isEqualTo(2);

    }

    @Test
    void shouldNotThrowExceptionIfValidGeolocation() {
        //given
        GeolocationRequest geolocationRequest = new GeolocationRequest();
        geolocationRequest.setLatitude(90);
        geolocationRequest.setLongitude(180);
        //then
        assertThat(
                getConstraintViolations(geolocationRequest).size()
        ).isEqualTo(0);

    }


}