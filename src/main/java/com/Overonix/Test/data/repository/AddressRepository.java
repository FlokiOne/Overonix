package com.Overonix.Test.data.repository;

import com.Overonix.Test.data.entity.EAddress;
import com.Overonix.Test.data.entity.EUser;
import com.Overonix.Test.presentation.dto.RqUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<EAddress, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM EAddress e " +
            "WHERE e.country = :country " +
            "OR e.lat = :lat " +
            "OR e.lon = :lon ")
    void delete(
            @Param("country") String country,
            @Param("lat") Double lat,
            @Param("lon") Double lon
    );


    @Query("select e from EAddress e " +
            "where e.id = :addressId")
    Optional<EAddress> findByAddressId(
            @Param("addressId") String addressId
    );

}