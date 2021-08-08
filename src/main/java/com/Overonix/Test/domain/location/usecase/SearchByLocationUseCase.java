package com.Overonix.Test.domain.location.usecase;

import com.Overonix.Test.config.NominatimConfig;
import com.Overonix.Test.presentation.dto.RqLocation;
import com.Overonix.Test.presentation.dto.RsAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class SearchByLocationUseCase {

    private final NominatimConfig nominatimConfig;
    private final RestTemplate restTemplate;
    private final GetJSON getJSON;

    @Autowired
    public SearchByLocationUseCase(NominatimConfig nominatimConfig, RestTemplate restTemplate, GetJSON getJSON) {
        this.nominatimConfig = nominatimConfig;
        this.restTemplate = restTemplate;
        this.getJSON = getJSON;
    }

    public RsAddress searchByLocation(RqLocation rqLocation) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        this.url(rqLocation),
                        String.class
                );

        return this.getJSON.getJSON(response.getBody());
    }

    private String url(RqLocation rqLocation) {
        String url = UriComponentsBuilder.fromHttpUrl(this.nominatimConfig.getReverse())
                .queryParam("lat", rqLocation.getLat())
                .queryParam("lon", rqLocation.getLon())
                .queryParam("format", this.nominatimConfig.getFormat())
                .queryParam("addressdetails", this.nominatimConfig.getAddressdetails())
                .toUriString();
        return url;
    }

}
