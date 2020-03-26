package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Cat;
import mimimimetr.entity.User;
import mimimimetr.repository.CatRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final @NonNull
    CatRepository catRepository;

    public void Save(Cat cat) {
        catRepository.save(cat);
    }

    public List<Cat> getNonVoted(final User user) {
        return catRepository.findAll();
    }

    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    public Cat getNext(User user) {
        return catRepository.findById(1L).get();
    }

}
