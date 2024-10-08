package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.UserCreationRequest;
import com.example.restaurant.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
   Users toUsers(UserCreationRequest userCreationRequest);
   
}
