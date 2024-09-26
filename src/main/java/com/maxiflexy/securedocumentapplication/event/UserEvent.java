package com.maxiflexy.securedocumentapplication.event;

import com.maxiflexy.securedocumentapplication.entity.UserEntity;
import com.maxiflexy.securedocumentapplication.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {

    private UserEntity user;
    private EventType type;
    private Map<?, ?> data;
}
