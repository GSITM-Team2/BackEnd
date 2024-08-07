package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.domain.bookmark.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String saveBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
        return bookmarkService.saveBookmark(idToken, festivalId);
    }

    @DeleteMapping()
    public String deleteBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
        return bookmarkService.deleteBookmark(idToken, festivalId);
    }

    @GetMapping("/all")
    public List<Bookmark> findUserBookmark(@RequestHeader String idToken) {
        return bookmarkService.findUserBookmark(idToken);
    }

}

