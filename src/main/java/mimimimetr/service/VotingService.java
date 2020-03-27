package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.dto.CatDto;
import mimimimetr.dto.CatResultsDto;
import mimimimetr.entity.CatEntity;
import mimimimetr.entity.UserEntity;
import mimimimetr.entity.VoteEntity;
import mimimimetr.form.CatResultForm;
import mimimimetr.form.CatVoteForm;
import mimimimetr.mapper.CatMapper;
import mimimimetr.mapper.CatResultMapper;
import mimimimetr.repository.CatRepository;
import mimimimetr.repository.VoteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

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

    public CatVoteForm nextCandidates(final UserEntity userEntity) {
        List<Long> catIdList = catRepository.findNextCandidateList(userEntity);
        if (catIdList != null && catIdList.size() >= 2) {
            Random r = new Random();
            Long id1 = (catIdList.remove(r.nextInt(catIdList.size())));
            Long id2 = (catIdList.get(r.nextInt(catIdList.size())));
            CatVoteForm catVoteForm = new CatVoteForm(CatMapper.INSTANCE.catToCatDto(catRepository.findById(id1).get()),
                    CatMapper.INSTANCE.catToCatDto(catRepository.findById(id2).get()));
            return catVoteForm;
        } else {
            return null;
        }
    }

    public List<CatResultForm> getResults() {
        List<CatResultForm> result = new LinkedList<>();
        for (Long x : catRepository.findWinnersIdList()){
            try {
                result.add(new CatResultForm(catService.getById(x), voteRepository.getVoteCount(x)));
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return result;
    }

    public CatResultsDto getVotesCount(CatEntity catEntity) {
        CatResultsDto result = CatResultMapper.INSTANCE.catToCatResultDto(catEntity);
        Long coutn = voteRepository.getVoteCount(result.getId());
        result.setVoteCount(coutn);
        return result;
    }

}
