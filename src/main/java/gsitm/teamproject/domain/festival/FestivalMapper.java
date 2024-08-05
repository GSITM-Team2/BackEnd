package gsitm.teamproject.domain.festival;

import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import gsitm.teamproject.dto.FestivalSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface FestivalMapper {

    int countAllWithFilter(FestivalSearchParam params);
    List<FestivalListResponseDto> findAllByFilter(FestivalSearchParam params);

    FestivalDetailResponse findById(Long id);
}
