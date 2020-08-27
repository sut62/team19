package com.project.backend.Character.Repository;

import com.project.backend.Character.Entity.Character;
import java.util.Collection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface CharacterRepository extends JpaRepository<Character, Long> {
  //Character findById(long id);
  /*@Query(
    value = "SELECT * FROM Character o where o.name = :name",
    nativeQuery = true
  )
  Collection<Character> findCharacterByName(@Param("name") String name);

  @Query(
    value = "SELECT Character.name FROM Character x where x.type_heroes = :type_heroes",
    nativeQuery = true
  )
  Collection<Character> findNameByType(
    @Param("type_heroes") String type_heroes
  );*/
}
