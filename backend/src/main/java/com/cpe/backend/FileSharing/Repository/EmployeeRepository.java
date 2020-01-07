package com.cpe.backend.FileSharing.Repository;
import com.cpe.backend.FileSharing.Entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
@CrossOrigin(origins = "http://localhost:8081")
@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findById(long id);
    @Query( value = "SELECT * FROM Employee o where o.username = :username and o.password = :password",
            nativeQuery = true)
    Collection<Employee> findEmployeeByUsername(@Param("username") String username,
    			@Param("password") String password);

}
