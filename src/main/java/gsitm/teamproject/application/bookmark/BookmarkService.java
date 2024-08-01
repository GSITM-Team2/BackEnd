package gsitm.teamproject.application.bookmark;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import gsitm.teamproject.domain.bookmark.Bookmark;
import gsitm.teamproject.domain.bookmark.BookmarkMapper;
import gsitm.teamproject.domain.bookmark.BookmarkRepository;
import gsitm.teamproject.domain.festival.Festival;
import gsitm.teamproject.domain.festival.FestivalRepository;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class BookmarkService {
    private final FestivalRepository festivalRepository;
    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(FestivalRepository festivalRepository, BookmarkMapper bookmarkMapper, BookmarkRepository bookmarkRepository) {
        this.festivalRepository = festivalRepository;
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
    }

    public ResponseEntity<String> saveBookmark(String idToken, Long festivalId) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            Festival festival = festivalRepository.findById(festivalId)
                    .orElseThrow(()->new NoSuchElementException("행사정보가 존재하지 않음"));

            Bookmark bookmark = new Bookmark(festival,uid);
            if (bookmarkRepository.findByUserIdAndFestivalId(uid,festivalId).isEmpty()) {
                bookmarkRepository.save(bookmark);
            } else {
                throw new IllegalArgumentException("북마크 중복 저장 불가");
            }
            return ResponseEntity.status(201).body("북마크가 저장되었습니다. id:" + bookmark.getBookmarkId());
        } catch (FirebaseAuthException e) {
            // Firebase 인증 실패하면
            return ResponseEntity.status(401).body("인증을 실패했습니다.: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(501).body("서버오류: " + e.getMessage());
        }
    }

    @Transactional
    public String deleteBookmark(@RequestHeader String idToken,
                                               @RequestParam Long festivalId) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        Festival festival = festivalRepository.findById(festivalId)
                .orElseThrow(()->new NoSuchElementException("행사정보가 존재하지 않음"));

        Bookmark bookmark = bookmarkRepository.findByUserIdAndFestivalId(uid,festivalId).orElse(null);
        if(bookmark==null){
            throw new NoSuchElementException("해당하는 북마크가 없습니다");
        }
        bookmarkRepository.delete(bookmark);
        return "북마크가 해제되었습니다.";
    }

    @Transactional(readOnly = true)
    public List<FestivalListResponseDto> findUserBookmark(String idToken) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        List<Bookmark> bookmarks = bookmarkRepository.findByUid(uid);
        if(bookmarks.isEmpty()){
            throw new NoSuchElementException("북마크를 찾을 수 없습니다");
        }
        List<FestivalListResponseDto> eventList = bookmarkMapper.findBookmark(uid);
        return eventList;
    }

}
