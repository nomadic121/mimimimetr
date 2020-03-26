package mimimimetr.mapper;

import mimimimetr.dto.CatResultsDto;
import mimimimetr.entity.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatResultMapper {

    CatResultMapper INSTANCE = Mappers.getMapper(CatResultMapper.class);

    CatResultsDto catToCatResultDto(Cat cat);

}
