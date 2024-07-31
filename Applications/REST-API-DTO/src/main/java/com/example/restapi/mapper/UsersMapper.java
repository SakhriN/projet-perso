package com.example.restapi.mapper;

import com.example.restapi.dto.UsersDTO;
import com.example.restapi.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUsers(UsersDTO usersDTO);

    UsersDTO toUsersDto(Users users);
}
