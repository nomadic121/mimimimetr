package mimimimetr.repository;

import mimimimetr.entity.CatEntity;
import mimimimetr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository<CatEntity, Long> {

    @Query(
            value = "SELECT * FROM CAT AS A LEFT JOIN (SELECT * FROM VOTING where author_id = ?1) AS B " +
                    "ON A.id = B.cat_id " +
                    "WHERE B.cat_id IS NULL",
            nativeQuery = true)
    List<CatEntity> findNextCandidates(@Param("author") UserEntity userEntity);

    @Query(
            value = "SELECT A.id FROM CAT_entity AS A LEFT JOIN (SELECT * FROM VOTE_entity where author_id = ?1) AS B ON A.id = B.cat_id WHERE B.cat_id IS NULL",
            nativeQuery = true)
    List<Long> findNextCandidateList(@Param("author") UserEntity userEntity);

    //TODO winners
    @Query(
            value = "SELECT * FROM CAT " +
                    "ORDER BY vote_count" +
                    "LIMIT 10",
            nativeQuery = true)
    List<CatEntity> findWinners();

}
