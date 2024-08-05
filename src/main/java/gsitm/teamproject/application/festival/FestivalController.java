package gsitm.teamproject.application.festival;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import gsitm.teamproject.dto.FestivalPagedResponseDto;
import gsitm.teamproject.dto.FestivalSearchParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/festivals")
public class FestivalController{
    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/filter")
    FestivalPagedResponseDto findAllByFilter(FestivalSearchParam searchParam) {
        return festivalService.findAllByFilters(searchParam);
    }

    @GetMapping("/{festivalId}")
    public FestivalDetailResponse findById(@PathVariable Long festivalId) {
        return festivalService.findById(festivalId);
    }


}