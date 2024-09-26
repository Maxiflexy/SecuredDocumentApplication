package com.maxiflexy.securedocumentapplication.repository;

import com.maxiflexy.securedocumentapplication.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> getCredentialByUserEntityId(Long userId);
}
