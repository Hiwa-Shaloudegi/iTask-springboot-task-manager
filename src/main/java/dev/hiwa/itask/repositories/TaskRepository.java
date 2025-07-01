package dev.hiwa.itask.repositories;

import dev.hiwa.itask.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByTaskList_Id(UUID taskListId);

    Optional<Task> findByIdAndTaskList_Id(UUID taskListId, UUID id);

}
