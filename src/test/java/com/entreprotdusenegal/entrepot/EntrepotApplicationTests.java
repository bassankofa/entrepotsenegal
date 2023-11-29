package com.entreprotdusenegal.entrepot;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



import com.entrepot.senegal.EntrepotApplication;
//import com.entrepot.senegal.config.SecurityConfig;
import com.entrepot.senegal.controller.UserController;
import com.entrepot.senegal.model.User;





@SpringBootTest(classes = EntrepotApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntrepotApplicationTests {

	//@Autowired
	//MockMvc mockMvc;
	//WebApplicationContext context;

	//WebTestClient testClient;
	
/*  @BeforeEach
    void setup() {
        testClient = MockMvcWebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
               // .defaultRequest(get("/").with(user))
                .configureClient()
                .build();
    }*/

	
	@Autowired
	WebApplicationContext wac; 

	WebTestClient client;

	@BeforeEach
	void setUp() {
		client = MockMvcWebTestClient.bindToApplicationContext(this.wac).build(); 
	}

	@Test
	public void testAffiche(@Autowired WebTestClient webTestClient, @LocalServerPort int port)throws Exception{
	

			URI uri = UriComponentsBuilder
				.fromHttpUrl("http://localhost:{port}/alluser")
				.buildAndExpand(port, "Spring")
				.toUri();

		webTestClient.get().uri(uri)
				.exchange()
				.expectBody(User.class)
				.consumeWith(result -> {
					 assertEquals(result,"gaye");
					// custom assertions (e.g. AssertJ)...
				});
				
				
		


	}

	@Test
	public void whenUserWithWrongCredentials_thenUnauthorizedPage ()throws Exception{
			
				//assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
				//assertTrue(response.getBody().contains("Unauthorized"));
	}

}
