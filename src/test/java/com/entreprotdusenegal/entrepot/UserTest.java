package com.entreprotdusenegal.entrepot;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.entrepot.senegal.EntrepotApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntrepotApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class UserTest {

@Autowired
MockMvc mockMvc;

@Test
@DisplayName("TestCase1 Check if spring security applies to the endpoint")
@WithMockUser(username ="arameba",password = "passer123", roles = {"USER"})
public void successIfSecurityApplies() throws Exception{ 

    mockMvc.perform(get("/alluser"))                       
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(authenticated().withUsername("arameba"))
    .andExpect(authenticated().withRoles("USER"))
    .andExpect(jsonPath("$",hasSize(3)));
    
}
    
}
