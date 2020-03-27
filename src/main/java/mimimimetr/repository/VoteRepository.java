package mimimimetr.repository;

import mimimimetr.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    @Query(
            value = "SELECT COUNT (*) FROM vote_entity WHERE vote_entity.cat_id = ?1 AND vote_entity.vote = true",
            nativeQuery = true)
    Long getVoteCountById(Long catId);

}
