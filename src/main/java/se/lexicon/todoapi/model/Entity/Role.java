package se.lexicon.todoapi.model.Entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false,unique = true)

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
