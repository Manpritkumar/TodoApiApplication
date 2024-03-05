package se.lexicon.todoapi.converter;

import se.lexicon.todoapi.model.Entity.Role;
import se.lexicon.todoapi.model.dto.RoleDTOView;

public class RoleConverterImpl implements RoleConverter{
    @Override
    public RoleDTOView toRoleDTOView(Role entity) {

       return RoleDTOView.builder()
               .id(entity.getId())
               .name(entity.getName())
               .build();
        /*RoleDTOView roleDTOView = new RoleDTOView();
        roleDTOView.setId(entity.getId());
        roleDTOView.setName(entity.getName());
        return roleDTOView; */
    }

    @Override
    public Role toRoleEntity(RoleDTOView dtoView) {

        return Role.builder()
                .id(dtoView.getId())
                .name(dtoView.getName())
                .build();
    }
}
