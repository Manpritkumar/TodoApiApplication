package se.lexicon.todoapi.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.model.Entity.Role;
import se.lexicon.todoapi.repository.RoleRepository;
@Component
public class RoleDataLoader implements CommandLineRunner {

    @Autowired         // inject role repository and with that we can call role class
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));

        //We can add more role if need



    }
}
