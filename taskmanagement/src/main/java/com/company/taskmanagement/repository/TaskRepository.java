
package com.company.taskmanagement.repository;

import com.company.taskmanagement.model.Task;
import com.company.taskmanagement.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // TODO: Cuenta la cantidad de tareas activas (con estado PENDING o IN_PROGRESS) de un developer
    @Query("SELECT COUNT(t) FROM Task t WHERE t.developer.id = :developerId AND t.status IN :statuses")
    long countActiveTasks(@Param("developerId") Long developerId, @Param("statuses") List<TaskStatus> statuses);

    // TODO: Obtiene todas las tareas activas (con estado PENDING o IN_PROGRESS) de un developer en SQL nativo
    @Query(value = "SELECT * FROM tasks WHERE developer_id = :developerId AND status IN (:statuses)", nativeQuery = true)
    List<Task> findActiveTasks(@Param("developerId") Long developerId, @Param("statuses") List<String> statuses);
}
