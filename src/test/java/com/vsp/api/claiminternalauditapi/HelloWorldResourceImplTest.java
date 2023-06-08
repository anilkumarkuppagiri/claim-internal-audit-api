package com.vsp.api.claiminternalauditapi;

import com.vsp.oauth.AccessToken;
import com.vsp.oauth.AccessTokenBuilder;
import com.vspglobal.cloud.VspService;
import com.vspglobal.cloud.oauth.VspOauthInterceptor;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This is an example of a JUnit test for a resource implementation.
 *
 * It bypasses OAuth checks by using a mock Spring bean configured in the setup() method.
 * If needed, specific values can be injected into the OAuth token (AccessToken).
 * An example of this is shown in the setup() method.
 *
 * See Spring's documentation on writing Spring MVC Tests
 * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloWorldResourceImpl.class)
public class HelloWorldResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VspOauthInterceptor oauthInterceptor;

    private AccessToken accessToken;

    @Before
    public void setUp() throws Exception {
        // This sets up a mock OAuth interceptor that will allow testing the controller without an OAuth token
        when(oauthInterceptor.preHandle(anyObject(), anyObject(), anyObject())).thenReturn(true);

        // Create a token to be passed to the controller
        accessToken = new AccessTokenBuilder().principal("tester").build();
    }

    @Test
    public void getHello() throws Exception {
        // Call the controller endpoint under test passing it the test token
        mockMvc.perform(get("/hello").requestAttr(VspService.RQST_ATTR_TOKEN, accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("//TODO: implement method getHello")));
    }

    @Test
    public void createHello() throws Exception {
        mockMvc.perform(post("/hello")
                .content("hello")
                .requestAttr(VspService.RQST_ATTR_TOKEN, accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("//TODO: implement method createHello")));
    }

    @Test
    public void updateHello() throws Exception {
        mockMvc.perform(put("/hello/{id}", "1234")
                .content("hello")
                .requestAttr(VspService.RQST_ATTR_TOKEN, accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("//TODO: implement method updateHello")));
    }

    @Test
    public void publicHello() throws Exception {
        mockMvc.perform(get("/hello/public"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("//TODO: implement method publicHello")));
    }

}
