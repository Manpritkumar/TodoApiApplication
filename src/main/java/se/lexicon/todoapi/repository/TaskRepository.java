package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.todoapi.model.Entity.Task;

public interface TaskRepository extends JpaRepository <Task,Long> {
}
