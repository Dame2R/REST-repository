package repo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.internal.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record.ForDefinedMethod.WithBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ExtendWith(SpringExtension.class)
@SpringBootTest

public class SpringbootBackendApplicationTests {
	
    @Autowired
	private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

	@BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	@Test
	 public void contextLoads() {
	}
	@Test
	public void testgetOverviewById() throws Exception {
        //Expected Output
        /*    
        "id": "Process_0vjv4zt",
        "childProcess": "Process_1kndcr1;",
        "startKnoten": "StartEvent_1;Event_1po129z;",
        "endKnoten": "Event_1a8blh9;",
        "energySumYear": 450,
        "processType": "CORE",
        "department": "Einkauf",
        "processDescription": "Dies ist eine Prozessbeschreibung\n        "
} */
	mockMvc.perform(get("/overview/Process_0vjv4zt")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"))
                .andExpect(jsonPath("$.id").value("Process_0vjv4zt"))
                .andExpect(jsonPath("$.childProcess").value("Process_1kndcr1;"))
                .andExpect(jsonPath("$.startKnoten").value("StartEvent_1;Event_1po129z;"))
                .andExpect(jsonPath("$.endKnoten").value("Event_1a8blh9;"))
                .andExpect(jsonPath("$.energySumYear").value(450))
                .andExpect(jsonPath("$.processType").value("CORE"))
                .andExpect(jsonPath("$.department").value("Einkauf"));
				
	}
    @Test
	public void testgetModelById() throws Exception {

	mockMvc.perform(get("/modell/Process_0vjv4zt")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8;"));
				
	}
    @Test
	public void testgetAllOverviews() throws Exception {

	mockMvc.perform(get("/overviews")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"));
				
	}
    @Test
	public void testgetProcessTypeCORE() throws Exception {

	mockMvc.perform(get("/overviews/processType?type=core")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"));
				
	}
    @Test
	public void testgetOverviewSortedByCO2() throws Exception {

	mockMvc.perform(get("/overviews?sortBy=CO2")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;"));
				
	}
    

	

}
