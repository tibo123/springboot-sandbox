package iblutha.demo.springboot.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by tibo on 07/11/2015.
 */
@Entity(name = "todo")
public class JpaTodo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_id_seq")
    @SequenceGenerator(name="todo_id_seq", sequenceName = "todo_id_seq", allocationSize = 100)
    private long id;
    @Column(nullable=false)
    private Integer expId;
    @Column(nullable=false)
    private String title;
    @Column
    private String description;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDateTime endDate;
    @Column
    private boolean isCurrent;


    protected JpaTodo(){}

    private JpaTodo(Builder builder){
        this.id = builder.id;
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

    public static Builder copyBuilder(JpaTodo todo) {
        return new Builder()
                .id(todo.getId())
                .expId(todo.getExpId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .isCurrent(todo.isCurrent());
    }

    public static class Builder  {
        long id;
        int expId;
        String title;
        String description;
        LocalDate startDate;
        LocalDateTime endDate;
        boolean isCurrent;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

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

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder isCurrent(boolean isCurrent) {
            this.isCurrent = isCurrent;
            return this;
        }

        public JpaTodo build() {
            return new JpaTodo(this);
        }
    }

    public long getId() {
        return this.id;
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

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public Boolean isCurrent() {
        return this.isCurrent;
    }

    public String toString() {
        return new StringBuilder("JpaTodo [")
                .append("id : ").append(id).append(",")
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
