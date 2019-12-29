package com.cpe.backend;

import com.cpe.backend.RentCar.Entity.RentType;
import com.cpe.backend.RentCar.Repository.RentTypeRepository;

import com.cpe.backend.Carreturn.Entity.Payforfine;
import com.cpe.backend.Carreturn.Repository.PayforfineRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	ApplicationRunner init(RentTypeRepository rentTypeRepository,PayforfineRepository payforfineRepository) {
		return args -> {
			Stream.of("รายวัน", "รายเดือน").forEach(namet -> {
				Stream.of(600, 18000).forEach(name -> {
					if(namet == "รายวัน" && name == 600){
						RentType rentType = new RentType();
						rentType.setNametype(namet);
						rentType.setUnitprice(name);
						rentTypeRepository.save(rentType);
					}else if(namet == "รายเดือน" && name == 18000){
						RentType rentType = new RentType();
						rentType.setNametype(namet);
						rentType.setUnitprice(name);
						rentTypeRepository.save(rentType);
					}
				});
			});

			Stream.of("มีความเสียหายภายนอก", "มีความเสียหายภายใน", "มีความเสียหายทั้งภายในและนอก", "ไม่มีการเสียหาย").forEach(description-> {
				Payforfine payforfine = new Payforfine(); 
				payforfine.setDescription(description); 
				payforfineRepository.save(payforfine); 
			});

			payforfineRepository.findAll().forEach(System.out::println);
			rentTypeRepository.findAll().forEach(System.out::println);
		};
	}

}
