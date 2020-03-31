package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.CatEntity;
import mimimimetr.entity.UserEntity;
import mimimimetr.entity.VoteEntity;
import mimimimetr.exeption.CatNotFoundExeption;
import mimimimetr.form.CatResultForm;
import mimimimetr.form.CatVoteForm;
import mimimimetr.mapper.CatMapper;
import mimimimetr.repository.CatRepository;
import mimimimetr.repository.VoteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VotingService {

    private final VoteRepository voteRepository;

    private final CatRepository catRepository;

    private final CatService catService;

    private final UserService userService;

    public boolean vote(final Principal principal, final Long like, final Long unLike) {
        try {
            UserEntity userEntity = userService.getByName(principal.getName());
            CatEntity catEntityLike = catRepository.getOne(like);
            CatEntity catEntityUnLike = catRepository.getOne(unLike);
            VoteEntity voteLike = VoteEntity.builder()
                    .author(userEntity)
                    .catEntity(catEntityLike)
                    .vote(true)
                    .build();
            VoteEntity voteUnLike = VoteEntity.builder()
                    .author(userEntity)
                    .catEntity(catEntityUnLike)
                    .vote(false).build();
            voteRepository.save(voteLike);
            voteRepository.save(voteUnLike);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    public CatVoteForm nextCandidates(final UserEntity userEntity) throws CatNotFoundExeption {
        List<Long> catIdList = catRepository.findNextCandidatesIdList(userEntity);
        if (catIdList != null && catIdList.size() >= 2) {
            Random r = new Random();
            Long id1 = (catIdList.remove(r.nextInt(catIdList.size())));
            Long id2 = (catIdList.get(r.nextInt(catIdList.size())));
            return new CatVoteForm(CatMapper.INSTANCE.catToCatDto(catRepository.findById(id1).orElseThrow(() ->
                    new CatNotFoundExeption("Cat not found. Id: " + id1.toString()))),
                    CatMapper.INSTANCE.catToCatDto(catRepository.findById(id2).orElseThrow(() ->
                            new CatNotFoundExeption("Cat not found. Id: " + id2.toString()))));
        } else {
            return null;
        }
    }

    public List<CatResultForm> getResults() throws CatNotFoundExeption {
        List<CatResultForm> result = new LinkedList<>();
        for (Long x : catRepository.findWinnersIdList()) {
            result.add(new CatResultForm(catService.getById(x), voteRepository.getVoteCountById(x)));
        }
        return result;
    }

}
