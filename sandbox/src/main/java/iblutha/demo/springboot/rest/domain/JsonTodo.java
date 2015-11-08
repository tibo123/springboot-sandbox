package iblutha.demo.springboot.rest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

/**
 * Created by tibo on 07/11/2015.
 */
@JsonDeserialize(builder=JsonTodo.Builder.class)
public class JsonTodo {

    private Integer expId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean isCurrent;

    private JsonTodo(Builder builder){
        this.expId = builder.expId;
        this.title = builder.title;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.isCurrent = builder.isCurrent;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static Builder copyBuilder(JsonTodo experience) {
        return new Builder()
                .expId(experience.getExpId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .isCurrent(experience.isCurrent());
    }
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        int expId;
        String title;
        String description;
        Date startDate;
        Date endDate;
        boolean isCurrent;

        public Builder expId(int expId) {
            this.expId = expId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String descritption) {
            this.description = descritption;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder isCurrent(boolean isCurrent) {
            this.isCurrent = isCurrent;
            return this;
        }

        public JsonTodo build() {
            return new JsonTodo(this);
        }
    }

    public Integer getExpId() {
        return this.expId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Boolean isCurrent() {
        return this.isCurrent;
    }

    public String toString() {
        return new StringBuilder("JsonTodo [")
                .append("expId : ").append(expId).append(",")
                .append("title : ").append(title).append(",")
                .append("description : ").append(description).append(",")
                .append("startDate : ").append(startDate).append(",")
                .append("endDate : ").append(endDate).append(",")
                .append("isCurrent : ").append(isCurrent)
                .append("]")
                .toString();
    }
}
