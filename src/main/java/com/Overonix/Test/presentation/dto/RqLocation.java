package com.Overonix.Test.presentation.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class RqLocation {
    @Max(value = 90, message = "Valid latitudes are to 90")
    @Min(value = -90, message = "Valid latitudes are to -90")
    private Double lat;
    @Max(value = 180, message = "Valid longitude are to 180")
    @Min(value = -180, message = "Valid longitude are from -180")
    private Double lon;
}
