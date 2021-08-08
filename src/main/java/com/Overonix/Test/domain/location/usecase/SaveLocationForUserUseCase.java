package com.Overonix.Test.domain.location.usecase;

import com.Overonix.Test.data.entity.EAddress;
import com.Overonix.Test.data.entity.EUser;
import com.Overonix.Test.data.repository.AddressRepository;
import com.Overonix.Test.data.repository.UserRepository;
import com.Overonix.Test.presentation.dto.RqUserAddress;
import com.Overonix.Test.presentation.dto.RsAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveLocationForUserUseCase {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public SaveLocationForUserUseCase(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public RsAddress saveAddress(RqUserAddress rqUserAddress) {
        EUser user = this.getUser(rqUserAddress);
        user.addAddress(new EAddress(rqUserAddress.country, rqUserAddress.lat, rqUserAddress.lon));

        this.userRepository.save(user);
        return new RsAddress(rqUserAddress.country, rqUserAddress.lat, rqUserAddress.lon);
    }

    public String removeAddress(String username, String addressId) {
        EUser user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        EAddress address = this.addressRepository.findByAddressId(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        user.removeAddress(address);
        this.addressRepository.delete(address);
        return "OK";
    }

    private EUser getUser(RqUserAddress rqUserAddress){
        return this.userRepository.findByUsername(rqUserAddress.username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
