package se.lexicon.todoapi.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTOView {
    private String email;

    private Set<RoleDTOView> roles;

}
