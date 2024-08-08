package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.domain.bookmark.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void saveBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
         bookmarkService.saveBookmark(idToken, festivalId);
    }

    @DeleteMapping()
    public void deleteBookmark(@RequestHeader String idToken, @RequestParam Long festivalId) {
        bookmarkService.deleteBookmark(idToken, festivalId);
    }

    @GetMapping("/all")
    public List<Bookmark> findUserBookmark(@RequestHeader String idToken) {
        return bookmarkService.findUserBookmark(idToken);
    }

}

