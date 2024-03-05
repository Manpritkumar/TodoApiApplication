package se.lexicon.todoapi.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(mappedBy = "person") // owner of relation is Task so in the persom we need to write mapped by
    private List<Task> tasks;

    public Person(String name) {
        this.name = name;
    }

    public void addTask(Task... tasks){
        if (Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks is empty");

        for (Task task: tasks){
            this.tasks.add(task);
            if (task.getPerson() != null){
                task.setPerson(this);
            }

        }

    }
    public void removeTask(Task... tasks){
        if (Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Tasks is empty");
        for(Task task : tasks){
            if (this.tasks.remove(task) && task.getPerson() == this){
                task.setPerson(null);
            }

        }


    }

}

