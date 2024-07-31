package gsitm.teamproject.domain.bookmark;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    @Query("SELECT b FROM Bookmark b WHERE b.uid = :uid AND b.festival = :festival_id")
    Optional<Bookmark> findByUserIdAndFestivalId(@Param("uid") String uid, @Param("festival_id") Long festivalId);

    List<Bookmark> findByUid(String uid);
}
