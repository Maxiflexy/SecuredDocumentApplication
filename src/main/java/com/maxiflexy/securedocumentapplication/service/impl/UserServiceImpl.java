package com.maxiflexy.securedocumentapplication.service.impl;

import com.maxiflexy.securedocumentapplication.entity.ConfirmationEntity;
import com.maxiflexy.securedocumentapplication.entity.CredentialEntity;
import com.maxiflexy.securedocumentapplication.entity.RoleEntity;
import com.maxiflexy.securedocumentapplication.entity.UserEntity;
import com.maxiflexy.securedocumentapplication.enums.Authority;
import com.maxiflexy.securedocumentapplication.enums.EventType;
import com.maxiflexy.securedocumentapplication.event.UserEvent;
import com.maxiflexy.securedocumentapplication.exception.ApiException;
import com.maxiflexy.securedocumentapplication.repository.ConfirmationRepository;
import com.maxiflexy.securedocumentapplication.repository.CredentialRepository;
import com.maxiflexy.securedocumentapplication.repository.RoleRepository;
import com.maxiflexy.securedocumentapplication.repository.UserRepository;
import com.maxiflexy.securedocumentapplication.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.maxiflexy.securedocumentapplication.utils.UserUtils.createUserEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
    //private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;


    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));

    }

    @Override
    public RoleEntity getRoleName(String name) {
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow( () -> new ApiException("Role not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }


}
