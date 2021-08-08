package com.Overonix.Test.domain.location;

import com.Overonix.Test.domain.location.usecase.GetUserLocationsUseCase;
import com.Overonix.Test.domain.location.usecase.SaveLocationForUserUseCase;
import com.Overonix.Test.domain.location.usecase.SearchByAddressUseCase;
import com.Overonix.Test.domain.location.usecase.SearchByLocationUseCase;
import com.Overonix.Test.presentation.dto.RqLocation;
import com.Overonix.Test.presentation.dto.RqUserAddress;
import com.Overonix.Test.presentation.dto.RsAddress;
import com.Overonix.Test.presentation.dto.RsUserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationService {

    private final SearchByAddressUseCase searchByAddressUseCase;
    private final SearchByLocationUseCase searchByLocationUseCase;
    private final SaveLocationForUserUseCase saveLocationForUserUseCase;
    private final GetUserLocationsUseCase getUserLocationsUseCase;

    @Autowired
    LocationService(SearchByAddressUseCase searchByAddressUseCase, SearchByLocationUseCase searchByLocationUseCase, SaveLocationForUserUseCase saveLocationForUserUseCase, GetUserLocationsUseCase getUserLocationsUseCase) {
        this.searchByAddressUseCase = searchByAddressUseCase;
        this.searchByLocationUseCase = searchByLocationUseCase;
        this.saveLocationForUserUseCase = saveLocationForUserUseCase;
        this.getUserLocationsUseCase = getUserLocationsUseCase;
    }

    public List<RsAddress> findByAddress(String address) {
        return searchByAddressUseCase.searchByAddress(address);
    }

    public RsAddress findByLocation(RqLocation rqLocation) {
        return searchByLocationUseCase.searchByLocation(rqLocation);
    }

    public RsAddress saveLocationForUser(RqUserAddress rqUserAddress) {
        return saveLocationForUserUseCase.saveAddress(rqUserAddress);
    }


    public String removeLocationFromUser(String username, String addressId) {
        return saveLocationForUserUseCase.removeAddress(username, addressId);
    }

    public RsUserAddress getAllUserAddress(String username) {
        return getUserLocationsUseCase.getAllUserAddress(username);
    }

}
