package gsitm.teamproject.domain.festival;

import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface FestivalMapper {

    List<FestivalListResponseDto> findAllPaginated(@Param("offset") int offset, @Param("limit") int limit);
    long getTotalCount();

    List<FestivalListResponseDto> findAllByFilter(String codename, String guname);

    FestivalDetailResponse findById(Long id);
}
