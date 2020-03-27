package mimimimetr.repository;

import mimimimetr.entity.CatEntity;
import mimimimetr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatRepository extends JpaRepository<CatEntity, Long> {

    @Query(
            value = "SELECT A.id FROM CAT_entity AS A LEFT JOIN " +
                    "(SELECT * FROM VOTE_entity where author_id = ?1) AS B ON A.id = B.cat_id WHERE B.cat_id IS NULL",
            nativeQuery = true)
    List<Long> findNextCandidatesIdList(UserEntity userEntity);

    @Query(
            value = "SELECT cat_id FROM vote_entity WHERE vote = TRUE GROUP BY cat_id ORDER BY COUNT(*) DESC LIMIT 10;",
            nativeQuery = true)
    List<Long> findWinnersIdList();

}
