package gsitm.teamproject.domain.bookmark;

import gsitm.teamproject.domain.festival.Festival;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne
    private Festival festival;

    private String uid;

    public Bookmark(Festival festival, String uid) {
        this.festival = festival;
        this.uid = uid;
    }
}
