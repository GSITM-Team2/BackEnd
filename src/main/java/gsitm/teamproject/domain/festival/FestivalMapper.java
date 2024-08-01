package gsitm.teamproject.domain.festival;

import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.*;

@Mapper
public interface FestivalMapper {

    List<FestivalListResponseDto> findAll();

    List<FestivalListResponseDto> findAllByFilter(String codename, String guname);

    FestivalDetailResponse findById(Long id);
}
