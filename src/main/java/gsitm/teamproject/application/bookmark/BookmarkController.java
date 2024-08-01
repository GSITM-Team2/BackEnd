package gsitm.teamproject.application.bookmark;


import com.google.firebase.auth.FirebaseAuthException;
import gsitm.teamproject.dto.FestivalListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping()
    public ResponseEntity<String> saveBookmark(@RequestHeader String idToken,
                                               @RequestParam Long festivalId) throws FirebaseAuthException{
        return bookmarkService.saveBookmark(idToken,festivalId);
    }

    @DeleteMapping()
    public String deleteBookmark(@RequestHeader String idToken,
                                 @RequestParam Long festivalId) throws FirebaseAuthException {
        return bookmarkService.deleteBookmark(idToken,festivalId);
    }

    @GetMapping("/all")
    public List<FestivalListResponseDto> findUserBookmark(@RequestHeader String idToken) throws FirebaseAuthException {
        return bookmarkService.findUserBookmark(idToken);
    }

}
