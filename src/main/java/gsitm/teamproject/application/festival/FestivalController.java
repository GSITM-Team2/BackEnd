package gsitm.teamproject.application.festival;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/festivals")
public class FestivalController{
    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/all")
    public List<FestivalListResponseDto> findAll (){
        return festivalService.findAll();
    }

    @GetMapping("/filter")
    List<FestivalListResponseDto> findAllByFilter(
            @RequestParam(required = false) String codename,
            @RequestParam(required = false) String guname) {
        return festivalService.findAllByFilter(codename, guname);
    }

    @GetMapping("/Festival/{event_id}")
    public FestivalDetailResponse findById(@PathVariable Long festivalId) {
        return festivalService.findById(festivalId);
    }


}