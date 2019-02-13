package formBased.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess
            (HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

         log.info("----Logging out the user: {}",auth.getName());
        response.setStatus(HttpServletResponse.SC_OK);
        //redirect to login page
        response.sendRedirect("/login?logout=true");
    }
}
