package iblutha.demo.springboot.mappers;

import iblutha.demo.springboot.api.Mapper;
import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.rest.domain.JsonTodo;

import javax.inject.Named;
import java.util.List;

/**
 * Created by tibo on 09/11/2015.
 */
@Named
public class TodoJsonToJpaMapper implements Mapper<JsonTodo, JpaTodo> {
    @Override
    public JpaTodo map(JsonTodo jsonTodo) {
        return JpaTodo.newBuilder()
                .expId(jsonTodo.getExpId())
                .title(jsonTodo.getTitle())
                .description(jsonTodo.getDescription())
                .startDate(jsonTodo.getStartDate())
                .endDate(jsonTodo.getEndDate())
                .isCurrent(jsonTodo.isCurrent())
                .build();
    }

    @Override
    public List<JpaTodo> mapAll(List<JsonTodo> from) {
        return null;
    }
}
