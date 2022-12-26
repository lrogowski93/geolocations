package com.demo.restapi.controller.dto;

import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class GeolocationRequestDtoTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    private Set<ConstraintViolation<GeolocationRequestDto>> getConstraintViolations(GeolocationRequestDto geolocationRequestDto) {
        return validator.validate(geolocationRequestDto);
    }


    @Test
    void shouldThrowExceptionIfNotValidGeolocation()
    {
        //given
        GeolocationRequestDto geolocationRequestDto = new GeolocationRequestDto();
        geolocationRequestDto.setLatitude(100);
        geolocationRequestDto.setLongitude(-500);
        //then
        assertThat(
                getConstraintViolations(geolocationRequestDto).size()
        ).isEqualTo(2);

    }

    @Test
    void shouldNotThrowExceptionIfValidGeolocation()
    {
        //given
        GeolocationRequestDto geolocationRequestDto = new GeolocationRequestDto();
        geolocationRequestDto.setLatitude(90);
        geolocationRequestDto.setLongitude(180);
        //then
        assertThat(
                getConstraintViolations(geolocationRequestDto).size()
        ).isEqualTo(0);

    }


}