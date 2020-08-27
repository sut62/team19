package com.project.backend.Character.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.backend.Character.Entity.Character;
import com.project.backend.Character.Repository.CharacterRepository;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CharacterController {
  @Autowired
  private final CharacterRepository characterRepository;

  CharacterController(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  @GetMapping("/character")
  public Collection<Character> Character() {
    return characterRepository.findAll().stream().collect(Collectors.toList());
  }

  /*@GetMapping("/character/{name}")
  public Collection<Character> getCharacterByName(
    @PathVariable("name") String name
  ) {
    return characterRepository.findCharacterByName(name);
  }

  @GetMapping("/character/{type_heroes}")
  public Collection<Character> getNameByType(
    @PathVariable("type_heroes") String type_heroes
  ) {
    return characterRepository.findNameByType(type_heroes);
  }*/

  @PutMapping("/character/{id}")
  public ResponseEntity<Character> updateCharacter(
    @PathVariable("id") long id,
    @RequestBody Character character
  ) {
    System.out.println("Update Customer with ID = " + id + "...");

    Optional<Character> characterData = characterRepository.findById(id);

    if (characterData.isPresent()) {
      Character _character = characterData.get();
      _character.setName(character.getName());
      _character.setImg_small(character.getImg_small());
      _character.setImg_big(character.getImg_big());

      return new ResponseEntity<>(
        characterRepository.save(_character),
        HttpStatus.OK
      );
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping(
    "/character/{name}/{img_small}/{img_big}/{img_skill}/{name_skill}/{detail_skill}/{img_skill_1_1}/{name_skill_1_1}/{detail_skill_1_1}/{img_skill_1_2}/{name_skill_1_2}/{detail_skill_1_2}/{img_skill_2_1}/{name_skill_2_1}/{detail_skill_2_1}/{img_skill_2_2}/{name_skill_2_2}/{detail_skill_2_2}/{img_skill_3_1}/{name_skill_3_1}/{detail_skill_3_1}/{img_skill_3_2}/{name_skill_3_2}/{detail_skill_3_2}/{type_heroes1}/{img_type_heroes1}/{type_heroes2}/{img_type_heroes2}"
  )
  public Character newCharacter(
    Character newCharacter,
    @PathVariable String name,
    @PathVariable String img_small,
    @PathVariable String img_big,
    @PathVariable String img_skill,
    @PathVariable String name_skill,
    @PathVariable String detail_skill,
    @PathVariable String img_skill_1_1,
    @PathVariable String name_skill_1_1,
    @PathVariable String detail_skill_1_1,
    @PathVariable String img_skill_1_2,
    @PathVariable String name_skill_1_2,
    @PathVariable String detail_skill_1_2,
    @PathVariable String img_skill_2_1,
    @PathVariable String name_skill_2_1,
    @PathVariable String detail_skill_2_1,
    @PathVariable String img_skill_2_2,
    @PathVariable String name_skill_2_2,
    @PathVariable String detail_skill_2_2,
    @PathVariable String img_skill_3_1,
    @PathVariable String name_skill_3_1,
    @PathVariable String detail_skill_3_1,
    @PathVariable String img_skill_3_2,
    @PathVariable String name_skill_3_2,
    @PathVariable String detail_skill_3_2,
    @PathVariable String type1_heroes,
    @PathVariable String img_type1_heroes,
    @PathVariable String type2_heroes,
    @PathVariable String img_type2_heroes
  ) {
    newCharacter.setName(name);
    newCharacter.setImg_small(img_small);
    newCharacter.setImg_big(img_big);

    newCharacter.setImg_skill(img_skill);
    newCharacter.setName_skill(name_skill);
    newCharacter.setDetail_skill(detail_skill);

    newCharacter.setImg_skill_1_1(img_skill_1_1);
    newCharacter.setName_skill_1_1(name_skill_1_1);
    newCharacter.setDetail_skill_1_1(detail_skill_1_1);
    newCharacter.setImg_skill_1_2(img_skill_1_2);
    newCharacter.setName_skill_1_2(name_skill_1_2);
    newCharacter.setDetail_skill_1_2(detail_skill_1_2);

    newCharacter.setImg_skill_2_1(img_skill_2_1);
    newCharacter.setName_skill_2_1(name_skill_2_1);
    newCharacter.setDetail_skill_2_1(detail_skill_2_1);
    newCharacter.setImg_skill_2_2(img_skill_2_2);
    newCharacter.setName_skill_2_2(name_skill_2_2);
    newCharacter.setDetail_skill_2_2(detail_skill_2_2);

    newCharacter.setImg_skill_3_1(img_skill_3_1);
    newCharacter.setName_skill_3_1(name_skill_3_1);
    newCharacter.setDetail_skill_3_1(detail_skill_3_1);
    newCharacter.setImg_skill_3_2(img_skill_3_2);
    newCharacter.setName_skill_3_2(name_skill_3_2);
    newCharacter.setDetail_skill_3_2(detail_skill_3_2);

    newCharacter.setType1_heroes(type1_heroes);
    newCharacter.setImg_type1_heroes(img_type1_heroes);
    newCharacter.setType2_heroes(type2_heroes);
    newCharacter.setImg_type2_heroes(img_type2_heroes);

    return characterRepository.save(newCharacter);
  }
}
