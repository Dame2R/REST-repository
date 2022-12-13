package repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootBackendApplicationTests {
	
    @Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
    void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	void contextLoads() {
	}
	@Test
	void testProcessOverview(){
        //Expected Output
        /*    
    "id": "Process_0vjv4zs",
    "childProcess": "Process_1kndcr1;Process_1kndcr1;",
    "startKnoten": "StartEvent_1;Event_1po129z;",
    "endKnoten": "Event_0jwg8pn;Event_1yedj2w;",
    "energySumYear": 50,
    "processType": "nicht core",
    "department": "Verkauf",
    "processDescription": "Dies ist eine Prozessbeschreibung\nad"
} */
/* 
	mockMvc.perform(get("/overview/Process_0vjv4zs")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"))
                .andExpect(jsonPath("$.id").value("Process_0vjv4zs"))
                .andExpect(jsonPath("$.childProcess").value("Process_1kndcr1;Process_1kndcr1;"))
                .andExpect(jsonPath("$.startKnoten").value("StartEvent_1;Event_1po129z;"))
                .andExpect(jsonPath("$.endKnoten").value("Event_0jwg8pn;Event_1yedj2w;"))
                .andExpect(jsonPath("$.energySumYear").value(50))
                .andExpect(jsonPath("$.processType").value("nicht core"))
                .andExpect(jsonPath("$.department").value("Verkauf"))
                .andExpect(jsonPath("$.processDescription").value("Dies ist eine Prozessbeschreibung\nad"));*/
				
	}

	

}
