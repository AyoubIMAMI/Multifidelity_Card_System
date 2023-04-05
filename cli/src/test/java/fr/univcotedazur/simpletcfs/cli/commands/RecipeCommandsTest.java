package fr.univcotedazur.simpletcfs.cli.commands;

import fr.univcotedazur.simpletcfs.cli.model.CliCustomer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(CliCustomer.class)
public class RecipeCommandsTest {
/*
    @Autowired
    private CliCustomer client;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void recipesSetTest() {

        server
                .expect(requestTo("customers/registration"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("", MediaType.APPLICATION_JSON));

        assertEquals(EnumSet.allOf(CookieEnum.class), client.recipes());
    }
*/
}
