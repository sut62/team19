package com.project.backend.Login.Repository;

import com.project.backend.Login.Entity.Login;
import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface LoginRepository extends JpaRepository<Login, Long> {
  Login findById(long id);

  @Query(
    value = "SELECT * FROM Login o where o.username = :username and o.password = :password",
    nativeQuery = true
  )
  Collection<Login> findLoginByUsername(
    @Param("username") String username,
    @Param("password") String password
  );
}
