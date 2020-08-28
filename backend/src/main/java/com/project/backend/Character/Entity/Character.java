package com.project.backend.Character.Entity;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CHARACTER")
public class Character {
  @Id
  @SequenceGenerator(name = "Character_seq", sequenceName = "Character_seq")
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "Character_seq"
  )
  @Column(name = "Character_id", unique = true, nullable = true)
  private Long id;

  @NotNull
  @Column(name = "NAME")
  private String name;

  @NotNull
  @Column(name = "IMG_SMALL")
  private String img_small;

  @NotNull
  @Column(name = "IMG_BIG")
  private String img_big;

  //============== Skill ติดตัว  ===========================
  @NotNull
  @Column(name = "IMG_SKILL")
  private String img_skill;

  @NotNull
  @Column(name = "NAME_SKILL")
  private String name_skill;

  @NotNull
  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL")
  private String detail_skill;

  //============== Skill 1.1  ============================
  @NotNull
  @Column(name = "IMG_SKILL_1_1")
  private String img_skill_1_1;

  @NotNull
  @Column(name = "NAME_SKILL_1_1")
  private String name_skill_1_1;

  @NotNull
  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_1_1")
  private String detail_skill_1_1;

  //============== Skill 1.2 (ถ้ามี) =======================
  @Column(name = "IMG_SKILL_1_2")
  private String img_skill_1_2;

  @Column(name = "NAME_SKILL_1_2")
  private String name_skill_1_2;

  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_1_2")
  private String detail_skill_1_2;

  //============== Skill 2.1  ============================
  @NotNull
  @Column(name = "IMG_SKILL_2_1")
  private String img_skill_2_1;

  @NotNull
  @Column(name = "NAME_SKILL_2_1")
  private String name_skill_2_1;

  @NotNull
  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_2_1")
  private String detail_skill_2_1;

  //============== Skill 2.2 (ถ้ามี) =======================
  @Column(name = "IMG_SKILL_2_2")
  private String img_skill_2_2;

  @Column(name = "NAME_SKILL_2_2")
  private String name_skill_2_2;

  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_2_2")
  private String detail_skill_2_2;

  //============== Skill 3.1  ============================
  @NotNull
  @Column(name = "IMG_SKILL_3_1")
  private String img_skill_3_1;

  @NotNull
  @Column(name = "NAME_SKILL_3_1")
  private String name_skill_3_1;

  @NotNull
  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_3_1")
  private String detail_skill_3_1;

  //============== Skill 3.2 (ถ้ามี) =======================
  @Column(name = "IMG_SKILL_3_2")
  private String img_skill_3_2;

  @Column(name = "NAME_SKILL_3_2")
  private String name_skill_3_2;

  @Size(min = 10, max = 1000)
  @Column(name = "DETAIL_SKILL_3_2")
  private String detail_skill_3_2;

  //==================== ประเภทฮีโร่ ========================
  @NotNull
  @Column(name = "IMG_TYPE1_HEROES")
  private String img_type1_heroes;

  @NotNull
  @Column(name = "TPYE1_HEROES")
  private String type1_heroes;

  @Column(name = "IMG_TYPE2_HEROES")
  private String img_type2_heroes;

  @Column(name = "TPYE2_HEROES")
  private String type2_heroes;
}
