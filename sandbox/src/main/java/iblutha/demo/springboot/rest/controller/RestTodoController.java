package iblutha.demo.springboot.rest.controller;

import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.jpa.repository.TodoRepository;
import iblutha.demo.springboot.mappers.TodoJpaToJsonMapper;
import iblutha.demo.springboot.mappers.TodoJsonToJpaMapper;
import iblutha.demo.springboot.rest.domain.JsonTodo;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by tibo on 07/11/2015.
 */
@RestController
@RequestMapping("/rest")
public class RestTodoController {

    private TodoRepository repository;
    private TodoJpaToJsonMapper todoJpaToJsonMapper;
    private TodoJsonToJpaMapper todoJsonToJpaMapper;

    @Inject
    public RestTodoController(TodoRepository repository, TodoJpaToJsonMapper todoJpaToJsonMapper, TodoJsonToJpaMapper todoJsonToJpaMapper) {
        this.repository = repository;
        this.todoJpaToJsonMapper = todoJpaToJsonMapper;
        this.todoJsonToJpaMapper = todoJsonToJpaMapper;
    }

    @RequestMapping("/todo")
    public void addTodo(@RequestBody @Valid final JsonTodo todo){
        repository.save(todoJsonToJpaMapper.map(todo));
    }

    @RequestMapping("/todo/{id}")
    public JsonTodo getTodo(@PathVariable("id") Long todoId) {
        JpaTodo jpaTodo = repository.findOne(todoId);
        return todoJpaToJsonMapper.map(jpaTodo);
    }

    @RequestMapping("/todos")
    public List<JsonTodo> getTodos() {
        List<JpaTodo> jpaTodos = repository.findAll();
        return todoJpaToJsonMapper.mapAll(jpaTodos);
    }

    @RequestMapping("/todo/search")
    public List<JsonTodo> getTodoByTitle(@RequestParam("title") String title) {
        List<JpaTodo> jpaTodos = repository.findByTitle(title);
        return todoJpaToJsonMapper.mapAll(jpaTodos);
    }
}
