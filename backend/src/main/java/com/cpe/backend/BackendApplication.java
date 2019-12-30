package com.cpe.backend;

import com.cpe.backend.RentCar.Entity.RentType;
import com.cpe.backend.RentCar.Repository.RentTypeRepository;

import com.cpe.backend.Carreturn.Entity.Payforfine;
import com.cpe.backend.Carreturn.Repository.PayforfineRepository;

import com.cpe.backend.Customer.Repository.JobRepository;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;
import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.Customer.Entity.RegisType;

import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;

import com.cpe.backend.Payment.Entity.PaymentOptions;
import com.cpe.backend.Payment.Repository.OptionsRepository;

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
	ApplicationRunner init(RentTypeRepository rentTypeRepository,PayforfineRepository payforfineRepository,JobRepository jobRepository,
		RegisTypeRepository regisTypeRepository,CustomerRepository customerRepository,EmployeeRepository employeeRepository,OptionsRepository optionsRepository) {
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

			Stream.of("รับราชการ", "รับจ้างทั่วไป", "ว่างงาน").forEach(name -> {
				Job job = new Job();
				job.setJob(name);
				jobRepository.save(job);
			});
			Stream.of("ชั่วคราว", "แบบธรรมดา" , "VIP").forEach(name -> {
				RegisType type = new RegisType();
				type.setType(name);
				regisTypeRepository.save(type);
			});

			Stream.of("ชำระเงินสด", "ชำระผ่าน Mobile Banking","ชำระผ่าน บัตรเครดิต/บัตรเดบิต").forEach(name -> {
				PaymentOptions options = new PaymentOptions();
				options.setName(name);
				optionsRepository.save(options);
			});

			Employee p1 = new  Employee();
			p1.setName("เด่นชัย ราชประสงค์");
			p1.setUsername("denchai");
			p1.setPassword("12345678");
			employeeRepository.save(p1);

			Employee p2 = new  Employee();
			p2.setName("บัวขาว บักแตงโม");
			p2.setUsername("buakao");
			p2.setPassword("12345678");
			employeeRepository.save(p2);

			employeeRepository.findAll().forEach(System.out::println);
			payforfineRepository.findAll().forEach(System.out::println);
			rentTypeRepository.findAll().forEach(System.out::println);
			optionsRepository.findAll().forEach(System.out::println);
		};
	}

}
