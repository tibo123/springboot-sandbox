package iblutha.demo.springboot;

import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.jpa.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.URL;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by tibo on 07/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SandboxApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class TodoIntegrationTest {
    @Value("${local.server.port}")
    private int port;

    @Inject
    private TodoRepository repository;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
//        repository.deleteAll();
        repository.save(
                Arrays.asList(
                        buildExperience(1, "Title 1", "Description 1"),
                        buildExperience(2, "Title 2", "Description 2"),
                        buildExperience(3, "Title 3", "Description 3"),
                        buildExperience(4, "Title 4", "Description 4"),
                        buildExperience(5, "Title 5", "Description 5"),
                        buildExperience(6, "Title 6", "Description 6")));
        assertThat(repository.findAll().size(),is(6));

        this.base = new URL("http://localhost:" + port + "/rest/todos");
        template = new TestRestTemplate();
    }

    @Test
    public void getExperience() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo(new String("[{\"expId\":1,\"title\":\"Title 1\",\"description\":\"Description 1\",\"startDate\":null,\"endDate\":null,\"current\":false},{\"expId\":2,\"title\":\"Title 2\",\"description\":\"Description 2\",\"startDate\":null,\"endDate\":null,\"current\":false},{\"expId\":3,\"title\":\"Title 3\",\"description\":\"Description 3\",\"startDate\":null,\"endDate\":null,\"current\":false},{\"expId\":4,\"title\":\"Title 4\",\"description\":\"Description 4\",\"startDate\":null,\"endDate\":null,\"current\":false},{\"expId\":5,\"title\":\"Title 5\",\"description\":\"Description 5\",\"startDate\":null,\"endDate\":null,\"current\":false},{\"expId\":6,\"title\":\"Title 6\",\"description\":\"Description 6\",\"startDate\":null,\"endDate\":null,\"current\":false}]")));
    }

    private JpaTodo buildExperience(int expId, String title, String description){
        return JpaTodo.newBuilder()
                .expId(expId)
                .title(title)
                .description(description)
                .build();
    }
}
