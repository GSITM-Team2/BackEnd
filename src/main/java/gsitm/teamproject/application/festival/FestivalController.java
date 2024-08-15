package gsitm.teamproject.application.festival;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalPagedResponseDto;
import gsitm.teamproject.dto.FestivalSearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/festivals")
public class FestivalController{
    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/filter")
    public FestivalPagedResponseDto findAllByFilter(FestivalSearchParam searchParam) {
        FestivalPagedResponseDto response = festivalService.findAllByFilters(searchParam);
        return response;
    }
    @GetMapping("/{festivalId}")
    public FestivalDetailResponse findById(@PathVariable Long festivalId) {
        return festivalService.findById(festivalId);
    }


}