package mimimimetr.repository;

import mimimimetr.entity.Cat;
import mimimimetr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    @Query(
            value = "SELECT * FROM CAT AS A LEFT JOIN (SELECT * FROM VOTING where author_id = ?1) AS B " +
                    "ON A.id = B.cat_id " +
                    "WHERE B.cat_id IS NULL",
            nativeQuery = true)
    List<Cat> findNextCandidates(@Param("author") User user);

    //TODO winners
    @Query(
            value = "SELECT * FROM CAT " +
                    "ORDER BY vote_count" +
                    "LIMIT 10",
            nativeQuery = true)
    List<Cat> findWinners();

}
