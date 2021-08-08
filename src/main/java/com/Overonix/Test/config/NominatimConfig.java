package com.Overonix.Test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("nominatim")
@Data
public class NominatimConfig {
    private String search;
    private String reverse;
    private Integer addressdetails;
    private String format;
}
