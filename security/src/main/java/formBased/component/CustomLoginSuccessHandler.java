package formBased.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if(session != null){
            final SavedRequest savedRequest = (SavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            final String redirectUrl = savedRequest.getRedirectUrl();
            if(redirectUrl != null){
                log.info("Login success Handler Redirect URL: "+redirectUrl);
                // we do not forget to clean this attribute from session
                session.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
                // then we redirect
                getRedirectStrategy().sendRedirect(request, response, String.valueOf(redirectUrl));
            }else{
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }else{
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
