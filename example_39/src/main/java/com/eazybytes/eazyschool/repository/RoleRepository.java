package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String name);
}
