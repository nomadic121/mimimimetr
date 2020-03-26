package mimimimetr.mapper;

import mimimimetr.dto.CatDto;
import mimimimetr.entity.CatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    CatDto catToCatDto(CatEntity catEntity);

}
