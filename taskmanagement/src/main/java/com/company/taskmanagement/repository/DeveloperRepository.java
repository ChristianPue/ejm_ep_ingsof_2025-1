package com.company.taskmanagement.repository;

import com.company.taskmanagement.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    // TODO: Verifica si existe un developer con un correo específico (HU01 Escenario 2: correo duplicado) - JPQL
    @Query("SELECT COUNT(d) > 0 FROM Developer d WHERE d.email = :email")
    boolean existsByEmailJPQL(@Param("email") String email);

    // TODO: Verifica si existe un developer con un correo específico (HU01 Escenario 2: correo duplicado) - SQL nativo
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM developers WHERE email = :email", nativeQuery = true)
    boolean existsByEmailSQL(@Param("email") String email);

    // TODO: Busca un developer por su correo  - JPQL
    @Query("SELECT d FROM Developer d WHERE d.email = :email")
    Optional<Developer> findByEmailJPQL(@Param("email") String email);

    // TODO: Busca un developer por su correo - SQL nativo
    @Query(value = "SELECT * FROM developers WHERE email = :email", nativeQuery = true)
    Optional<Developer> findByEmailSQL(@Param("email") String email);
}
