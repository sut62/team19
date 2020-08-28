package com.project.backend;

import com.project.backend.Character.Entity.Character;
import com.project.backend.Character.Repository.CharacterRepository;
import com.project.backend.Login.Entity.Login;
import com.project.backend.Login.Repository.LoginRepository;
import java.util.stream.Stream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Bean
  ApplicationRunner init(LoginRepository loginRepository,CharacterRepository characterRepository) {
    return args -> {
      Login p1 = new Login();
      p1.setName("Nattapol Panta");
      p1.setUsername("namenp2707");
      p1.setPassword("27072541");
      p1.setEmail("namenp09@gmail.com");
      loginRepository.save(p1);

      //======================= Airi ===================================
      //================================================================
      Character c1 = new Character();
      c1.setName("Airi");
      c1.setImg_small("..\\Heroes\\Airi\\airi-s.png");
      c1.setImg_big("..\\Heroes\\Airi\\airi.png");
      c1.setImg_skill("..\\Heroes\\Airi\\skill-airi.png");
      c1.setName_skill("Dragon Blade");
      c1.setDetail_skill(
        "การโจมตีปกติแต่ละครั้ง จะลดคูลดาวน์สกิล Dragon Shuriken 1 วินาที"
      );
      c1.setImg_skill_1_1("..\\Heroes\\AiRi\\skill-airi1.png");
      c1.setName_skill_1_1("Dragon Shuriken");
      c1.setDetail_skill_1_1(
        "Airi ปาดาวกระจายใส่เหล่าศัตรูเบื้องหน้า สร้างความเสียหายกายภาพ"
      );
      c1.setImg_skill_2_1("..\\Heroes\\AiRi\\skill-airi2.png");
      c1.setName_skill_2_1("Swift Shadow");
      c1.setDetail_skill_2_1(
        "Airi พุ่งไปโจมตีจุดที่กำหนด สร้างความเสียหายกายภาพโดยสกิลนี้สามารถใช้ต่อเนื่องได้ 3 ครั้ง ก่อนที่สกิลจะคูลดาวน์"
      );
      c1.setImg_skill_3_1("..\\Heroes\\AiRi\\skill-airi3.png");
      c1.setName_skill_3_1("Ryo no Ikari");
      c1.setDetail_skill_3_1(
        "Airi กระโดดขึ้นไปบนอากาศ พร้อมกับเรียกมังกรออกมาโจมตีสร้างความเสียหายกายภาพ และลดความเร็ว จากนั้นสร้างความเสียหายซ้ำอีกครั้ง พร้อมกับสตั๊นศัตรู (การโจมตีแต่ละครั้งจะเพิ่มเกราะ)ขณะใช้สกิลจะไม่ติดสถานะ และความเร็วเพิ่มขึ้น 100% เป็นเวลา 2.5 วิ; ความเร็วโจมตี 30% เป็นเวลา 5 วิ"
      );
      c1.setImg_type1_heroes("..\\assasin.png");
      c1.setType1_heroes("Assassin");
      c1.setImg_type2_heroes("..\\fighter.png");
      c1.setType2_heroes("Fighter");
      characterRepository.save(c1);

      //======================= Aleister ===============================
      //================================================================
      Character c2 = new Character();
      c2.setName("Aleister");
      c2.setImg_small("..\\Heroes\\Aleister\\Aleister-s.png");
      c2.setImg_big("..\\Heroes\\Aleister\\Aleister.png");
      c2.setImg_skill("..\\Heroes\\Aleister\\skill-0.png");
      c2.setName_skill("Wicked Plot");
      c2.setDetail_skill(
        "ทุกการโจมตีปกติครั้งที่ 3 หรือการใช้สกิลของ Aleister จะทำให้เป้าหมายติดคำสาป เมื่อติดคำสาป 3 ครั้ง จะทำให้เกิดระเบิด สร้างความเสียหายจริง"
      );
      c2.setImg_skill_1_1("..\\Heroes\\Aleister\\skill-1.png");
      c2.setName_skill_1_1("Magic Barrier");
      c2.setDetail_skill_1_1(
        "Aleister สร้างแผงสายฟ้าดักหน้าศัตรู เมื่อศัตรูวิ่งผ่านจะสร้างความเสียหายเวทพร้อมกับทำให้ติดสต๊ันและคำสาป 1 ครั้ง หลังจากนั้นจะทำให้วิ่งช้าลงเป็นเวลาสั้นๆ"
      );
      c2.setImg_skill_2_1("..\\Heroes\\Aleister\\skill-2.png");
      c2.setName_skill_2_1("Matrix of Woe");
      c2.setDetail_skill_2_1(
        "Aleister สร้างพื้นที่สายฟ้าขึ้นมาช็อตศัตรูอย่างต่อเนื่อง โดยการโจมตีแต่ละครั้งจะสร้างความเสียหายเวทและติดคำสาป 1 ครั้ง"
      );
      c2.setImg_skill_3_1("..\\Heroes\\Aleister\\skill-3.png");
      c2.setName_skill_3_1("Ryo no Ikari");
      c2.setDetail_skill_3_1(
        "Aleister กักขังศัตรูในคุกเวท โดยจะสร้างความเสียหายเวทแต่ละครั้ง พร้อมกับทำให้ติดสตัน 2.5 วินาที (การโจมตีแต่ละครั้งทำให้ติดคำสาป 1 ครั้ง)"
      );
      c2.setImg_type1_heroes("..\\mage.png");
      c2.setType1_heroes("Mage");
      characterRepository.save(c2);

      loginRepository.findAll().forEach(System.out::println);
    };
  }
}
