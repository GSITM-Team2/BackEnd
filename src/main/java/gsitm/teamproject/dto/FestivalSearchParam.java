package gsitm.teamproject.dto;

public record FestivalSearchParam(
        Integer pageNumber,
        Integer pageSize,
        String codename,
        String guname
        ) {
    public FestivalSearchParam {
        pageNumber = pageNumber == null || pageNumber < 1 ? 1 : pageNumber;
        pageSize = pageSize == null || pageSize < 1 ? 12 : pageSize;
    }

    public Integer offset() {
        return (pageNumber - 1) * pageSize;
    }
}