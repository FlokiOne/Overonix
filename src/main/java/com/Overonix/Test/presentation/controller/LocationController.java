package com.Overonix.Test.presentation.controller;

import com.Overonix.Test.domain.location.LocationService;
import com.Overonix.Test.presentation.dto.RqLocation;
import com.Overonix.Test.presentation.dto.RqUserAddress;
import com.Overonix.Test.presentation.dto.RsAddress;
import com.Overonix.Test.presentation.dto.RsUserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/search-address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<RsAddress> searchByAddress(
            @RequestParam(value = "address", required = false) String address
    ) {
        return locationService.findByAddress(address);
    }

    @GetMapping("/search-location")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public RsAddress searchByLocation(@Valid @RequestBody RqLocation rqLocation) {
        return locationService.findByLocation(rqLocation);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public RsAddress saveForUser(@Valid @RequestBody RqUserAddress rqUserAddress) {
        return this.locationService.saveLocationForUser(rqUserAddress);
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String remove(@RequestParam String username, @RequestParam String addressId) {
        return this.locationService.removeLocationFromUser(username, addressId);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public RsUserAddress getAllForUser(@RequestParam String username) {
        return this.locationService.getAllUserAddress(username);
    }
}
