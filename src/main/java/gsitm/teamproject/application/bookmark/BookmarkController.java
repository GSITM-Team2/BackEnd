package gsitm.teamproject.application.bookmark;

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
    public ResponseEntity<Map<String, Object>> saveBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
        return bookmarkService.saveBookmark(idToken, festivalId);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> deleteBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
        return bookmarkService.deleteBookmark(idToken, festivalId);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findUserBookmark(@RequestHeader String idToken) {
        return bookmarkService.findUserBookmark(idToken);
    }

}

