package iblutha.demo.springboot.mappers;

import iblutha.demo.springboot.api.Mapper;
import iblutha.demo.springboot.jpa.domain.JpaTodo;
import iblutha.demo.springboot.rest.domain.JsonTodo;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tibo on 08/11/2015.
 */
@Named
public class TodoJpaToJsonMapper implements Mapper<JpaTodo, JsonTodo> {
    @Override
    public JsonTodo map(JpaTodo jpaTodo) {
        return JsonTodo.newBuilder()
                .expId(jpaTodo.getExpId())
                .title(jpaTodo.getTitle())
                .description(jpaTodo.getDescription())
                .startDate(jpaTodo.getStartDate())
                .endDate(jpaTodo.getEndDate())
                .isCurrent(jpaTodo.isCurrent())
                .build();
    }

    @Override
    public List<JsonTodo> mapAll(List<JpaTodo> from) {
        List<JsonTodo> experiences = new ArrayList<>(from.size());
        for (JpaTodo exp : from) {
            experiences.add(map(exp));
        }
        return experiences;
    }
}
