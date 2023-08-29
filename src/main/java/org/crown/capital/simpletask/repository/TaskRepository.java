package org.crown.capital.simpletask.repository;

import org.crown.capital.simpletask.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Iterable<Task> findAllByIsDeletedFalse();
}