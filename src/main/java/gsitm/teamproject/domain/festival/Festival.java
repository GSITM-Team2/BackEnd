package gsitm.teamproject.domain.festival;
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

    @Builder
    public Festival(Long id,
                    String orgName,
                    String useFee,
                    String player,
                    String orgLink,
                    String mainImg,
                    String themeCode,
                    String date,
                    String guname,
                    String etcDesc,
                    String endDate,
                    String title,
                    String codeName,
                    String userTrgt,
                    String program,
                    String place,
                    String startDate,
                    String isFree){
        this.id = id;
        this.orgName = orgName;
        this.useFee = useFee;
        this.player = player;
        this.orgLink = orgLink;
        this.guname = guname;
        this.mainImg = mainImg;
        this.themeCode = themeCode;
        this.date = date;
        this.etcDesc = etcDesc;
        this.endDate = endDate;
        this.title = title;
        this.codeName = codeName;
        this.userTrgt = userTrgt;
        this.program = program;
        this.startDate = startDate;
        this.place = place;
        this.isFree = isFree;
    }
}
