package mimimimetr.repository;

import mimimimetr.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    @Query(
            value = "SELECT COUNT (*) FROM VOTING " +
                    "WHERE VOTING.cat_id = ?1 AND VOTING.vote = true",
            nativeQuery = true)
    int getVoteCount(@Param("catId") Long catId);

}
