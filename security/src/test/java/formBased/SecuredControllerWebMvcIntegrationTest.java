package formBased;

import formBased.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HomeController.class)
@ComponentScan("formBased")
public class SecuredControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenRequestOnPrivateService_shouldFailWith401() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/home")
            .contentType(MediaType.ALL_VALUE))
            .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(value = "user")
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/home")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
