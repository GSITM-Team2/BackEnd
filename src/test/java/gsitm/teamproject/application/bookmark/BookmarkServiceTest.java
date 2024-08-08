package gsitm.teamproject.application.bookmark;

import gsitm.teamproject.auth.AuthenticationService;
import gsitm.teamproject.domain.bookmark.Bookmark;
import gsitm.teamproject.domain.bookmark.BookmarkRepository;
import gsitm.teamproject.domain.festival.Festival;
import gsitm.teamproject.domain.festival.FestivalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.NoSuchElementException;
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

    private final String idToken = "testIdToken";
    private final String uid = "testUid";
    private final Long festivalId = 1L;
    private Festival festival;

    @BeforeEach
    void setUp() {
        festival = Festival.builder()
                .id(festivalId)
                .build();
        festivalRepository.save(festival);
        when(authenticationService.authenticateAndGetUid(idToken)).thenReturn(uid);
    }

    @Test
    @DisplayName("행사정보가 존재하고 북마크가 중복되지 않으면 북마크가 저장된다")
    void saveBookmark_success() {
        // Given : 행사정보가 존재하고 <uid, festival> 이 같은 북마크가 존재하지 않는다
        when(festivalRepository.findById(festivalId)).thenReturn(Optional.of(festival));
        when(bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId)).thenReturn(Optional.empty());

        // When
        bookmarkService.saveBookmark(idToken, festivalId);

        // Then
        ArgumentCaptor<Bookmark> bookmarkCaptor = ArgumentCaptor.forClass(Bookmark.class);
        verify(bookmarkRepository).save(bookmarkCaptor.capture());
        Bookmark savedBookmark = bookmarkCaptor.getValue();

        assertThat(savedBookmark).isNotNull();
        assertThat(savedBookmark.getFestival()).isEqualTo(festival);
        assertThat(savedBookmark.getUid()).isEqualTo(uid);
    }

    @Test
    @DisplayName("북마크가 중복되면 저장에 실패한다")
    void saveBookmark_fail() {
        // Given
        when(festivalRepository.findById(festivalId)).thenReturn(Optional.of(festival));
        when(bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId)).thenReturn(Optional.of(new Bookmark()));

        // When&Then
        assertThatThrownBy(() -> bookmarkService.saveBookmark(idToken, festivalId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("북마크 중복 저장 불가");

    }

    @Test
    @DisplayName("북마크가 존재할 때 삭제에 성공한다")
    void deleteBookmark_success(){
        //Given
        Bookmark bookmark = bookmarkRepository.save(new Bookmark(festival,uid));
        when(bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId)).thenReturn(Optional.empty());

        //
        assertThat(bookmark).isNotNull().isEqualTo(true);
        bookmarkRepository.delete(bookmark);
        //then

        assertThat(bookmark).isNotNull().isEqualTo(false);
    }

    @Test
    @DisplayName("북마크가 없을 때 삭제에 실패한다")
    void deleteBookmark_fail(){
        //Give
        when(bookmarkRepository.findByUserIdAndFestivalId(uid, festivalId))
                .thenReturn(Optional.empty());

        //when&then
        assertThatThrownBy(()->bookmarkService.deleteBookmark(idToken,festivalId))
                .isInstanceOf(NoSuchElementException.class);

    }

}
