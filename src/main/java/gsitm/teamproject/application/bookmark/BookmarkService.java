package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.auth.AuthenticationService;
import gsitm.teamproject.domain.bookmark.Bookmark;
import gsitm.teamproject.domain.bookmark.BookmarkRepository;
import gsitm.teamproject.domain.festival.Festival;
import gsitm.teamproject.domain.festival.FestivalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class BookmarkService {
    private final FestivalRepository festivalRepository;
    private final BookmarkRepository bookmarkRepository;
    private final AuthenticationService authenticationService;

    public BookmarkService(FestivalRepository festivalRepository,
                           BookmarkRepository bookmarkRepository,
                           AuthenticationService authenticationService) {
        this.festivalRepository = festivalRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public void saveBookmark(String idToken, Long festivalId) {
        String uid = authenticationService.authenticateAndGetUid(idToken);

        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(() -> new NoSuchElementException("행사정보가 존재하지 않음"));

        if (bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId).isPresent()) {
            throw new IllegalArgumentException("북마크 중복 저장 불가");
        }
        Bookmark bookmark = new Bookmark(festival, uid);
        bookmarkRepository.save(bookmark);

    }

    @Transactional
    public void deleteBookmark(String idToken, Long festivalId) {
        String uid = authenticationService.authenticateAndGetUid(idToken);

        Bookmark bookmark = bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId)
                .orElseThrow(() -> new NoSuchElementException("북마크를 찾을 수 없습니다"));

        bookmarkRepository.delete(bookmark);
    }

    public List<Bookmark> findUserBookmark(String idToken) {
        String uid = authenticationService.authenticateAndGetUid(idToken);

        List<Bookmark> bookmarks = bookmarkRepository.findByUid(uid);
        if (bookmarks.isEmpty()) {
            throw new NoSuchElementException("북마크를 찾을 수 없습니다");
        }

        return bookmarks;
    }

}