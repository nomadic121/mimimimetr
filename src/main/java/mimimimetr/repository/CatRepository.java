package mimimimetr.repository;

import mimimimetr.entity.CatEntity;
import mimimimetr.entity.UserEntity;
import mimimimetr.form.ResultForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

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

    @Query(
            value = "SELECT cat_id FROM vote_entity WHERE vote = TRUE GROUP BY cat_id ORDER BY COUNT(*) DESC LIMIT 10;",
            nativeQuery = true)
    List<Long> findWinnersIdList(); //<id, count>

     @Query(
            value = "SELECT c_id, vote_count, cat_entity.image, cat_entity.name FROM cat_entity LEFT JOIN (SELECT cat_id AS c_id, COUNT(*) AS vote_count FROM vote_entity WHERE vote = TRUE GROUP BY cat_id ORDER BY vote_count LIMIT 10) AS vote_list ON cat_entity.id = vote_list.c_id;",
            nativeQuery = true)
    List findWinnersCat();

}
