package gsitm.teamproject.application.festival;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalPagedResponseDto;
import gsitm.teamproject.dto.FestivalSearchParam;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/festivals")
public class FestivalController{
    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    private static final Logger logger = LoggerFactory.getLogger(FestivalController.class);

    @GetMapping("/filter")
    public FestivalPagedResponseDto findAllByFilter(FestivalSearchParam searchParam) {
        logger.info("Received request with params: {}", searchParam);
        FestivalPagedResponseDto response = festivalService.findAllByFilters(searchParam);
        logger.info("Response: {}", response);
        return response;
    }
    @GetMapping("/{festivalId}")
    public FestivalDetailResponse findById(@PathVariable Long festivalId) {
        return festivalService.findById(festivalId);
    }


}