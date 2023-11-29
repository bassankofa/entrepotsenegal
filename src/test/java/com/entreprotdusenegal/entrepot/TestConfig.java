package com.entreprotdusenegal.entrepot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class TestConfig {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getUserTest() throws Exception{
        
         mockMvc.perform(get("/alluser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("gaye")))
                .andDo(MockMvcResultHandlers.print());
    }
    
}
