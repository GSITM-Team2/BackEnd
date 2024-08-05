package gsitm.teamproject.config;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class ExceptionHandler {

    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        if (e instanceof FirebaseAuthException) {
            response.put("status", 401);
            response.put("message", "인증을 실패했습니다.: " + e.getMessage());
            return ResponseEntity.status(401).body(response);
        } else if (e instanceof NoSuchElementException) {
            response.put("status", 404);
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        } else if (e instanceof IllegalArgumentException) {
            response.put("status", 400);
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        } else {
            response.put("status", 501);
            response.put("message", "서버오류: " + e.getMessage());
            return ResponseEntity.status(501).body(response);
        }
    }
}