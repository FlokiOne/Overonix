package com.Overonix.Test.presentation.dto;

import lombok.Data;

@Data
public class RsAddress {
    public String country;
    public Double lat;
    public Double lon;

    public RsAddress(String country, Double lat, Double lon) {
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

}
