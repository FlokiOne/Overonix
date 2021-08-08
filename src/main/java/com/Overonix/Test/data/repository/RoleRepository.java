package com.Overonix.Test.data.repository;


import com.Overonix.Test.data.entity.ERole;
import com.Overonix.Test.data.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<ERole, Integer> {

    Optional<ERole> findByName(RoleType name);
}
