package gsitm.teamproject.dto;
import java.util.List;
public record FestivalPagedResponseDto(
        int totalPage,
        int totalCount,
        int pageNumber,
        int pageSize,
        List<FestivalListResponseDto> festivals
) {
}
