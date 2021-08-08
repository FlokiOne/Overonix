package com.Overonix.Test.data.repository;

import com.Overonix.Test.data.entity.EAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<EAddress, Integer> {

    @Query("select e from EAddress e " +
            "where e.id = :addressId")
    Optional<EAddress> findByAddressId(
            @Param("addressId") String addressId
    );

}