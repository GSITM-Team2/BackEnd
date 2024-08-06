package gsitm.teamproject.application.festival;
import gsitm.teamproject.domain.festival.FestivalMapper;
import gsitm.teamproject.domain.festival.FestivalRepository;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import gsitm.teamproject.dto.FestivalPagedResponseDto;
import gsitm.teamproject.dto.FestivalSearchParam;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FestivalService {

    FestivalRepository festivalRepository;
    FestivalMapper festivalMapper;

    public FestivalService(FestivalRepository festivalRepository, FestivalMapper festivalMapper) {
        this.festivalRepository = festivalRepository;
        this.festivalMapper = festivalMapper;
    }

    public FestivalDetailResponse findById(Long id) {
        return festivalMapper.findById(id);

    }
    public FestivalPagedResponseDto findAllByFilters(FestivalSearchParam params) {
        int totalCount = festivalMapper.countAllWithFilter(params);
        int totalPages = (int) Math.ceil((double) totalCount / params.pageSize());

        List<FestivalListResponseDto> festivals = festivalMapper.findAllByFilter(params);

        return new FestivalPagedResponseDto(
                totalPages,
                totalCount,
                params.pageNumber(),
                params.pageSize(),
                festivals
        );
    }
}
