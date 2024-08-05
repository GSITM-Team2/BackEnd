package gsitm.teamproject.application.bookmark;


import gsitm.teamproject.auth.AuthenticationService;
import gsitm.teamproject.config.ExceptionHandler;
import gsitm.teamproject.domain.bookmark.Bookmark;
import gsitm.teamproject.domain.bookmark.BookmarkMapper;
import gsitm.teamproject.domain.bookmark.BookmarkRepository;
import gsitm.teamproject.domain.festival.Festival;
import gsitm.teamproject.domain.festival.FestivalRepository;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class BookmarkService {
    private final FestivalRepository festivalRepository;
    private final BookmarkMapper bookmarkMapper;
    private final BookmarkRepository bookmarkRepository;
    private final AuthenticationService authenticationService;

    private final ExceptionHandler exceptionHandler;


    public BookmarkService(FestivalRepository festivalRepository,
                           BookmarkMapper bookmarkMapper,
                           BookmarkRepository bookmarkRepository,
                           AuthenticationService authenticationService, ExceptionHandler exceptionHandler) {
        this.festivalRepository = festivalRepository;
        this.bookmarkMapper = bookmarkMapper;
        this.bookmarkRepository = bookmarkRepository;
        this.authenticationService = authenticationService;
        this.exceptionHandler = exceptionHandler;
    }

    public ResponseEntity<Map<String, Object>> saveBookmark(String uid, Long festivalId) {
        try {
            Festival festival = festivalRepository.findById(festivalId)
                    .orElseThrow(() -> new NoSuchElementException("행사정보가 존재하지 않음"));

            Bookmark bookmark = new Bookmark(festival, uid);
            if (bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId).isEmpty()) {
                bookmarkRepository.save(bookmark);
            } else {
                throw new IllegalArgumentException("북마크 중복 저장 불가");
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", 201);
            response.put("message", "북마크가 저장되었습니다. id:" + bookmark.getBookmarkId());
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> deleteBookmark(String idToken, Long festivalId) {
        try {
            String uid = authenticationService.authenticateAndGetUid(idToken);
            Bookmark bookmark = bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId).orElse(null);
            if (bookmark == null) {
                throw new NoSuchElementException("북마크를 찾을 수 없습니다");
            }
            bookmarkRepository.delete(bookmark);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "북마크가 해제되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

    public ResponseEntity<Map<String, Object>> findUserBookmark(String idToken) {
        try {
            String uid = authenticationService.authenticateAndGetUid(idToken);
            List<Bookmark> bookmarks = bookmarkRepository.findByUid(uid);
            if (bookmarks.isEmpty()) {
                throw new NoSuchElementException("북마크를 찾을 수 없습니다");
            }
            List<FestivalListResponseDto> bookmarkDtos = bookmarkMapper.findBookmark(uid);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("bookmarks", bookmarkDtos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return exceptionHandler.handleException(e);
        }
    }

}