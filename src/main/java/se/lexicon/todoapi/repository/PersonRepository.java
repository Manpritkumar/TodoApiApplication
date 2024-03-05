package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.model.Entity.Person;

@Repository
public interface PersonRepository extends JpaRepository <Person,Long> {
//find people who do not have any task

    @Query("select p from Person p where size(p.tasks)=0")
    List<Person> findIdlePeople();

}
