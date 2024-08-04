package gsitm.teamproject.application.festival;
import gsitm.teamproject.dto.FestivalDetailResponse;
import gsitm.teamproject.dto.FestivalListResponseDto;
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

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Map<String, Object> response = festivalService.findAllPaginated(page, size);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/filter")
    List<FestivalListResponseDto> findAllByFilter(
            @RequestParam(required = false) String codename,
            @RequestParam(required = false) String guname) {
        return festivalService.findAllByFilter(codename, guname);
    }

    @GetMapping("/{festivalId}")
    public FestivalDetailResponse findById(@PathVariable Long festivalId) {
        return festivalService.findById(festivalId);
    }


}