package com.cpe.backend.Maintenance.Repository;
import com.cpe.backend.Maintenance.Entity.Repairman;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public
interface RepairmanRepository extends JpaRepository<Repairman, Long> {
        Repairman findById(long id);
     @Query( value = "SELECT * FROM Repairman o where o.username = :username and o.password = :password",
    nativeQuery = true)
    Collection<Repairman> findRepairmanByUsername(@Param("username") String username,
    @Param("password") String password);
}
