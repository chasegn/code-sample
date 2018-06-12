package com.charter.enterprise.motd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MotdControllerTest {
    @Autowired
    private MockMvc mvc;

    private static String origMotd = "Welcome to Charter.  All systems are nominal.";
    private static String newMotd = "Goodbye world!";

    @Before
    public void resetMotd() throws Exception {
        mvc.perform(put("/").content(origMotd).contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getMotd() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(origMotd)));
    }

    @Test
    public void testNewMotd() throws Exception {
        mvc.perform(put("/").content(newMotd).contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isAccepted());

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(newMotd)));
    }
}
