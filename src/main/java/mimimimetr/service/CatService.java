package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.dto.CatDto;
import mimimimetr.entity.CatEntity;
import mimimimetr.exeption.CatNotFoundExeption;
import mimimimetr.mapper.CatMapper;
import mimimimetr.repository.CatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    public void save(CatEntity catEntity) {
        catRepository.save(catEntity);
    }

    public CatDto getById(Long id) throws CatNotFoundExeption {
        return CatMapper.INSTANCE.catToCatDto(catRepository.findById(id).orElseThrow(() ->
                new CatNotFoundExeption("Cat not found. Id: " + id)));
    }

}
