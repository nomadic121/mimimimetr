package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.CatEntity;
import mimimimetr.repository.CatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    public void save(CatEntity catEntity) {
        catRepository.save(catEntity);
    }

}
