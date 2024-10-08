package com.maxiflexy.securedocumentapplication.repository;

import com.maxiflexy.securedocumentapplication.entity.ConfirmationEntity;
import com.maxiflexy.securedocumentapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Long> {
    Optional<ConfirmationEntity> findByKey(String key);
    Optional<ConfirmationEntity> findByUserEntity(UserEntity userEntity);
}
