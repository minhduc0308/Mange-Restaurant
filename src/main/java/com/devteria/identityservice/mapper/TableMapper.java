package com.devteria.identityservice.mapper;

import com.devteria.identityservice.dto.response.TableResponse;
import com.devteria.identityservice.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "reservationTime", source = "reservationTime")
    TableResponse toResponse(Table table);

    List<TableResponse> toResponseList(List<Table> tables);
}
