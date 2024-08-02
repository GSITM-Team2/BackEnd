package gsitm.teamproject.application.festival;
import gsitm.teamproject.domain.festival.FestivalMapper;
import gsitm.teamproject.domain.festival.FestivalRepository;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
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

    public List<FestivalListResponseDto> findAll() {
        return festivalMapper.findAll();
    }

    public List<FestivalListResponseDto> findAllByFilter(String codename, String guname) {
        return festivalMapper.findAllByFilter(codename, guname);

    }
//    public List<FestivalListResponseDto> findByTitle(String title) {
//        List<Festival> festivals = FestivalRepository.findByTitle();
//        List<FestivalListResponseDto> eventList = festivals.stream()
//                .map(f -> new FestivalListResponseDto(
//                        f.getTitle(),
//                        f.getDate(),
//                        f.getPlace(),
//                        f.getMainImg(),
//                        f.getId())).toList();// e.getEvent_id() 추가
//        return eventList;
//    } 이 부분을 findByFilter 구현부랑 합쳐야함

    public FestivalDetailResponse findById(Long id) {
        return festivalMapper.findById(id);

    }
}