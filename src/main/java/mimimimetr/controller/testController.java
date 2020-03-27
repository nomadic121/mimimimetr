package mimimimetr.controller;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.CatEntity;
import mimimimetr.repository.CatRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class testController {

    private final CatRepository catRepository;

    @GetMapping("winners")
    public List get (){
        return catRepository.findWinnersIdList();
    }

}
