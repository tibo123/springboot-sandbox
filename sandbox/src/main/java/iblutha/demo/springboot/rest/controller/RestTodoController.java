package iblutha.demo.springboot.rest.controller;

import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.jpa.repository.TodoRepository;
import iblutha.demo.springboot.mappers.TodoJpaToJsonMapper;
import iblutha.demo.springboot.rest.domain.JsonTodo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by tibo on 07/11/2015.
 */
@RestController
@RequestMapping("/rest")
public class RestTodoController {

    @Inject
    private TodoRepository repository;
    @Inject
    private TodoJpaToJsonMapper mapper;

    @RequestMapping("/todo/{id}")
    public JsonTodo getTodo(@PathVariable("id") Long todoId) {
        JpaTodo jpaTodo = repository.findOne(todoId);
        return mapper.map(jpaTodo);
    }

    @RequestMapping("/todos")
    public List<JsonTodo> getTodos() {
        List<JpaTodo> jpaTodos = repository.findAll();
        return mapper.mapAll(jpaTodos);
    }
}
