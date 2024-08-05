package gsitm.teamproject.auth;

import com.google.firebase.auth.FirebaseAuthException;

public interface AuthenticationService {
    String authenticateAndGetUid(String idToken) throws FirebaseAuthException;
}
