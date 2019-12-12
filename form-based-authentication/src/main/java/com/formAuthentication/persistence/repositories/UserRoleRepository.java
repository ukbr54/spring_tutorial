package com.formAuthentication.persistence.repositories;

import com.formAuthentication.persistence.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,UserRole.Id> {
}
