package com.example.restapi.mapper;

import com.example.restapi.dto.SessionsDTO;
import com.example.restapi.entity.Sessions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SessionsMapper {
    @Mapping(source = "usersId", target = "users.usersId")
    Sessions toSessions(SessionsDTO sessionsDTO);
    @Mapping(source = "users.usersId", target = "usersId")
    SessionsDTO toSessionsDto(Sessions sessions);
}
