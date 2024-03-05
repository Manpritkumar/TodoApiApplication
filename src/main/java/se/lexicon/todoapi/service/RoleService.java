package se.lexicon.todoapi.service;

import se.lexicon.todoapi.model.Entity.Role;
import se.lexicon.todoapi.model.dto.RoleDTOView;

import java.util.List;

public interface RoleService {
    List<RoleDTOView> getAll();
}
