package com.sample.tokencounter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TokencounterApplicationTests {

    @Autowired
    private MockMvc mvc;

        @Test
    public void testGetToken() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/auth").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).andExpect(content().string(containsString("token")));
    }

    @Test
    public void testBadPath() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/apath").accept(MediaType.APPLICATION_JSON).header("Authorization", "12345")).
                andExpect(status().isUnauthorized()).andExpect(content().string(containsString("Invalid Token: Error in service")));
    }

}
