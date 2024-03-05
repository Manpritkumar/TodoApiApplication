package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.model.Entity.Role;
import se.lexicon.todoapi.model.dto.RoleDTOView;


@Component
public interface RoleConverter {
    RoleDTOView toRoleDTOView(Role entity);

    Role toRoleEntity(RoleDTOView dtoView);

}
