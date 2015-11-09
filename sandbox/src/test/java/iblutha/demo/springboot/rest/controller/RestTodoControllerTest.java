package iblutha.demo.springboot.rest.controller;

import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.jpa.repository.TodoRepository;
import iblutha.demo.springboot.mappers.TodoJpaToJsonMapper;
import iblutha.demo.springboot.mappers.TodoJsonToJpaMapper;
import iblutha.demo.springboot.rest.domain.JsonTodo;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tibo on 07/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class RestTodoControllerTest extends TestCase {

    private MockMvc mvc;
    @InjectMocks
    private RestTodoController experienceController;
    @Mock
    private TodoRepository repository;
    @Mock
    private TodoJpaToJsonMapper todoJpaToJsonMapper;
    @Mock
    private TodoJsonToJpaMapper todoJsonToJpaMapper;

    @Before
    public void setUp() throws Exception {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(experienceController).build();
    }

    @Test
    public void testAddTodo() throws Exception {
        JpaTodo jpaTodo = JpaTodo.newBuilder()
                .expId(1)
                .title("Title 1")
                .description("Description 1")
                .startDate(null)
                .endDate(null)
                .isCurrent(false)
                .build();

        when(todoJsonToJpaMapper.map(any(JsonTodo.class))).thenReturn(jpaTodo);
        when(repository.save(any(JpaTodo.class))).thenReturn(jpaTodo);

        mvc.perform(MockMvcRequestBuilders.post("/rest/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"expId\":1,\"title\":\"Title 1\",\"description\":\"Description 1\",\"startDate\":\"24-12-2015\",\"endDate\":\"24-12-2015 15:10\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<JsonTodo> jsonTodoCaptor = ArgumentCaptor.forClass(JsonTodo.class);
        verify(todoJsonToJpaMapper, times(1)).map(jsonTodoCaptor.capture());
        verify(repository, times(1)).save(eq(jpaTodo));

        verifyNoMoreInteractions(todoJsonToJpaMapper, repository);

        assertThat(jsonTodoCaptor.getValue().getExpId(), is(1));
        assertThat(jsonTodoCaptor.getValue().getTitle(), is("Title 1"));
        assertThat(jsonTodoCaptor.getValue().getDescription(), is("Description 1"));
        assertThat(jsonTodoCaptor.getValue().getStartDate(), is(LocalDate.of(2015,12,24)));
        assertThat(jsonTodoCaptor.getValue().getEndDate(), is(LocalDate.of(2015,12,24).atTime(15,10)));
        assertThat(jsonTodoCaptor.getValue().isCurrent(), is(Boolean.FALSE));
    }

    @Test
    public void testGetTodo() throws Exception {
        JpaTodo jpaTodo = mock(JpaTodo.class);

        JsonTodo jsonTodo = JsonTodo.newBuilder()
                .expId(1)
                .title("Title 1")
                .description("Description 1")
                .startDate(null)
                .endDate(null)
                .isCurrent(false)
                .build();

        when(repository.findOne(anyLong())).thenReturn(jpaTodo);
        when(todoJpaToJsonMapper.map(any(JpaTodo.class))).thenReturn(jsonTodo);

        mvc.perform(MockMvcRequestBuilders.get("/rest/todo/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("{\"expId\":1,\"title\":\"Title 1\",\"description\":\"Description 1\",\"startDate\":null,\"endDate\":null,\"current\":false}"))
                );

        verify(repository,times(1)).findOne(eq(1l));
        verify(todoJpaToJsonMapper,times(1)).map(eq(jpaTodo));
        verifyNoMoreInteractions(repository,todoJpaToJsonMapper, jpaTodo);
    }

    public void testGetTodos() throws Exception {

    }
}