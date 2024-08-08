package gsitm.teamproject.domain.festival;
import gsitm.teamproject.domain.bookmark.Bookmark;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Festival {

    @Id
    private Long id;
    private String orgName;
    private String useFee;
    private String player;
    private String orgLink;
    private String guname;
    private String mainImg;
    private String themeCode;
    private String date;
    private String etcDesc;
    private String endDate;
    private String title;
    private String codeName;
    private String userTrgt;
    private String program;
    private String startDate;
    private String place;
    private String isFree;
    private Long click;


    //테스트용 빌더
    @Builder
    public Festival(Long id){
        this.id = id;
    }
}
