package com.Overonix.Test.data.repository;


import com.Overonix.Test.data.entity.EUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EUser, Integer> {

    @Query(
            "select e from EUser e " +
                    "left join fetch e.address " +
                    "where e.username = :username"
    )
    Optional<EUser> findByUsernameWithAddress(String username);

    Optional<EUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
