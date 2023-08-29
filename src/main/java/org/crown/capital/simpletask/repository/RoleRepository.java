package org.crown.capital.simpletask.repository;

import org.crown.capital.simpletask.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
