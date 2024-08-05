package gsitm.teamproject.auth;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationService authenticationService;

    public LoginResolver(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader(HttpHeaders.AUTHORIZATION);

        String[] tokenFormat = authorization.split(" ");
        String tokenType = tokenFormat[0];
        String token = tokenFormat[1];

        if (tokenType.equals("Bearer") == false) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }

       return authenticationService.authenticateAndGetUid(token);

    }
}
