package se.lexicon.todoapi.service;

import se.lexicon.todoapi.model.dto.UserDTOForm;
import se.lexicon.todoapi.model.dto.UserDTOView;

public interface UserService {
    //register user( email,password,roles)
    //find by email
    //disable the user by email
    //enable the user by email

    UserDTOView register (UserDTOForm userDTOForm);

    void disableByEmail (String email);

    void enableByEmail (String email);
}
