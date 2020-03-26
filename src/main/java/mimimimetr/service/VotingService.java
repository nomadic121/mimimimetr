package mimimimetr.service;

import lombok.RequiredArgsConstructor;
import mimimimetr.dto.CatResultsDto;
import mimimimetr.entity.Cat;
import mimimimetr.entity.User;
import mimimimetr.entity.Voting;
import mimimimetr.mapper.CatResultMapper;
import mimimimetr.repository.CatRepository;
import mimimimetr.repository.VoteRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VotingService {

    private final @NonNull
    VoteRepository voteRepository;

    private final @NonNull
    CatRepository catRepository;

    private final @NonNull
    CatService catService;

    private final @NonNull
    UserService userService;

    public void vote(final User user, final Long like, final String pair) {
        if (pair != null || pair.contains(like.toString())) {
            String[] cats = pair.split(":");
            for (String x : cats) {
                if (like == Long.valueOf(x)) {
                    Cat catLike = catRepository.getOne(like);
                    Voting vote1 = Voting.builder().author(user).cat(catLike).vote(true).build();
                    voteRepository.save(vote1);
                } else {
                    Cat catOpponent = catRepository.getOne(Long.valueOf(x));
                    Voting vote2 = Voting.builder().author(user).cat(catOpponent).vote(false).build();
                    voteRepository.save(vote2);
                }
            }
        }
    }

    public List<Cat> nextCandidates(final User user) {
        List<Cat> catList = catRepository.findNextCandidates(user);
        if (catList != null && catList.size() >= 2) {
            List<Cat> catsPair = new LinkedList<>();
            Random r = new Random();
            catsPair.add(catList.remove(r.nextInt(catList.size())));
            catsPair.add(catList.get(r.nextInt(catList.size())));
            return catsPair;
        } else {
            return null;
        }
    }

    public List<CatResultsDto> getResults() {
        return catRepository.findAll().stream() //TODO winners
                .map(this::getVotesCount)
                .collect(toList());
    }

    public CatResultsDto getVotesCount(Cat cat) {
        CatResultsDto result = CatResultMapper.INSTANCE.catToCatResultDto(cat);
        int coutn = voteRepository.getVoteCount(result.getId());
        result.setVoteCount(coutn);
        return result;
    }

}
