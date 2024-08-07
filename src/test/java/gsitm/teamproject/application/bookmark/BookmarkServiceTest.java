package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.auth.AuthenticationService;
import gsitm.teamproject.domain.bookmark.Bookmark;
import gsitm.teamproject.domain.bookmark.BookmarkRepository;
import gsitm.teamproject.domain.festival.Festival;
import gsitm.teamproject.domain.festival.FestivalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private FestivalRepository festivalRepository;

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Test
    void saveBookmark_Success() {
        // Given
        //when
        //then
    }
}
