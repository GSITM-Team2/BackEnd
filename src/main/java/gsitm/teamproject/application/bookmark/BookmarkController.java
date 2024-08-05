package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.auth.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> saveBookmark(@LoginUser String uid, @RequestParam Long festivalId) {
        return bookmarkService.saveBookmark(uid, festivalId);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> deleteBookmark(@LoginUser String uid, @RequestParam Long festivalId) {
        return bookmarkService.deleteBookmark(uid, festivalId);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findUserBookmark(@LoginUser String uid) {
        return bookmarkService.findUserBookmark(uid);
    }

}
