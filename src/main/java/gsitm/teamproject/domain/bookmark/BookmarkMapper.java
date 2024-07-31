package gsitm.teamproject.domain.bookmark;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkMapper {
    List<FestivalListResponseDto> findBookmark(String uid) ;
}
