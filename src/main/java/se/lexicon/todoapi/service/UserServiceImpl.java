package se.lexicon.todoapi.service;

import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.exception.DataDuplicateException;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.model.Entity.Role;
import se.lexicon.todoapi.model.Entity.User;
import se.lexicon.todoapi.model.dto.RoleDTOView;
import se.lexicon.todoapi.model.dto.UserDTOForm;
import se.lexicon.todoapi.model.dto.UserDTOView;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder= passwordEncoder;
    }


    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        //check params
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null");
        //check if email already exists
        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if(isExistEmail) throw new DataDuplicateException("Email already exists");
        //Retrieve and validate roles
        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("This Role is not valid")))
                .collect(Collectors.toSet());
                )


        //Hash password
        User user = new User(userDTOForm.getEmail(),passwordEncoder.encode(userDTOForm.getPassword()));
for (Role role : roleList) {
    user.addRole(role);
}
        //Convert dto to entity


        //Create a new user entity and save it to database
        User saveUser = userRepository.save(user);
        //Convert saved user (entity) to dto view

        Set<RoleDTOView> roleDTOViews =saveUser.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                .collect(Collectors.toSet());

        return UserDTOView.builder()
                .email(saveUser.getEmail())
                .roles(roleDTOViews)
                .build();

    }

  /*  @Override
    public UserDTOView getByEmail(String email) {



        return null;
    }*/

    @Override
    public void disableByEmail(String email) {
        if(userRepository.existsByEmail(email)) throw new DataNotFoundException("Email dose not exist");
        userRepository.updateExpiredByEmail(email,false);

    }

    @Override
    public void enableByEmail(String email) {
        if(userRepository.existsByEmail(email)) throw new DataNotFoundException("Email dose not exist");
        userRepository.updateExpiredByEmail(email,true);

    }
}
