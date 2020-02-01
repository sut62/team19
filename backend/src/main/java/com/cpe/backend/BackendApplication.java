package com.cpe.backend;

import com.cpe.backend.RentCar.Entity.RentType;
import com.cpe.backend.RentCar.Entity.RentCar;
import com.cpe.backend.RentCar.Repository.RentCarRepository;
import com.cpe.backend.RentCar.Repository.RentTypeRepository;

import com.cpe.backend.Carreturn.Entity.Payforfine;
import com.cpe.backend.Carreturn.Entity.ReturnsCar;
import com.cpe.backend.Carreturn.Repository.ReturnsCarRepository;
import com.cpe.backend.Carreturn.Repository.PayforfineRepository;

import com.cpe.backend.Customer.Repository.JobRepository;
import com.cpe.backend.Customer.Repository.RegisTypeRepository;
import com.cpe.backend.Customer.Entity.Customer;
import com.cpe.backend.Customer.Repository.CustomerRepository;
import com.cpe.backend.Customer.Entity.Job;
import com.cpe.backend.Customer.Entity.RegisType;

import com.cpe.backend.Carname.Entity.Brand;
import com.cpe.backend.Carname.Entity.Car;
import com.cpe.backend.Carname.Entity.Province;
import com.cpe.backend.Carname.Entity.Carseat;
import com.cpe.backend.Carname.Repository.CarRepository;
import com.cpe.backend.Carname.Repository.BrandRepository;
import com.cpe.backend.Carname.Repository.ProvinceRepository;
import com.cpe.backend.Carname.Repository.CarseatRepository;

import com.cpe.backend.FileSharing.Entity.Employee;
import com.cpe.backend.FileSharing.Repository.EmployeeRepository;

import com.cpe.backend.Payment.entity.PaymentOptions;
import com.cpe.backend.Payment.entity.Payment;
import com.cpe.backend.Payment.repository.PaymentRepository;
import com.cpe.backend.Payment.repository.OptionsRepository;

import com.cpe.backend.Maintenance.Entity.Repairman;
import com.cpe.backend.Maintenance.Entity.Service;
import com.cpe.backend.Maintenance.Entity.Maintenance;
import com.cpe.backend.Maintenance.Repository.MaintenanceRepository;
import com.cpe.backend.Maintenance.Repository.RepairmanRepository;
import com.cpe.backend.Maintenance.Repository.ServiceRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	ApplicationRunner init(RentTypeRepository rentTypeRepository, PayforfineRepository payforfineRepository, JobRepository jobRepository,
		RegisTypeRepository regisTypeRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, 
		OptionsRepository optionsRepository, RepairmanRepository repairmanRepository, ServiceRepository serviceRepository,
		BrandRepository brandRepository, ProvinceRepository provinceRepository, CarseatRepository carseatRepository, CarRepository carRepository,
		RentCarRepository rentcarRepository, PaymentRepository paymentRepository, ReturnsCarRepository returnsCarRepository,
		MaintenanceRepository maintenanceRepository
		) {
		return args -> {

			Stream.of("Toyota Vigo", "Honda civic", "Susuki swift", "Mclaren P1","Mitsubishi Pajero").forEach(name -> {
				Brand brand = new Brand(); 
				brand.setBrand(name); 
				brandRepository.save(brand); 
			});
			Stream.of("กระบี่", "กรุงเทพมหานคร", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร",
    					"ขอนแก่น",
    					"จันทบุรี",
    					"ฉะเชิงเทรา",
    					"ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่",
    					"ตรัง", "ตราด", "ตาก",
    					"นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี", "นราธิวาส", "น่าน",
    					"บึงกาฬ", "บุรีรัมย์",
    					"ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
    					"พระนครศรีอยุธยา", "พะเยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่",
    					"ภูเก็ต",
    					"มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน",
   					 	"ยโสธร", "ยะลา",
    					"ร้อยเอ็ด", "ระนอง", "ระยอง", "ราชบุรี",
    					"ลพบุรี", "ลำปาง", "ลำพูน", "เลย",
    					"ศรีสะเกษ",
    					"สกลนคร", "สงขลา", "สตูล", "สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์",
    					"หนองคาย", "หนองบัวลำภู",
    					"อ่างทอง", "อำนาจเจริญ", "อุดรธานี", "อุตรดิตถ์", "อุทัยธานี", "อุบลราชธานี").forEach(provincee -> {
				Province province = new Province(); 
				province.setProvince(provincee); 
				provinceRepository.save(province); 
			});
			
			Stream.of("2ที่นั่ง","4 ที่นั่ง", "8 ที่นั่ง", "11 ที่นั่ง").forEach(cartypee -> {
				Carseat carseat = new Carseat(); 
				carseat.setCarseat(cartypee); 
				carseatRepository.save(carseat);
			});

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

			Stream.of("ตรวจสอบทั่วไป", "เกี่ยวกับช่วงล่าง/เบรก/ล้อ", "เกี่ยวกับระบบไฟฟ้า","เกี่ยวกับเครื่องยนต์","เกี่ยวกับตัวถัง").forEach(name -> {
				Service service = new Service();
				service.setService(name);
				serviceRepository.save(service);
			});

			Repairman r1 = new Repairman();
			r1.setRepairman("สมยศ จันทร์ส่องฟ้า");
			r1.setUsername("somyot");
			r1.setPassword("sy12345678");
			repairmanRepository.save(r1);

			Repairman r2 = new Repairman();
			r2.setRepairman("ประวุฒ รักเพื่อนพ้อง");
			r2.setUsername("pravut");
			r2.setPassword("pv12345678");
			repairmanRepository.save(r2);

			Repairman r3 = new Repairman();
			r3.setRepairman("สมศักดิ์ จิตรดี");
			r3.setUsername("somsak");
			r3.setPassword("ss12345678");
			repairmanRepository.save(r3);

			//Data car
			Car newCar = new Car();
			Carseat carseat = carseatRepository.findById(1);
    		Brand brandd = brandRepository.findById(1);
    		Province province = provinceRepository.findById(1);
    		Employee employee = employeeRepository.findById(1);

    		newCar.setPlate("กข1234");
    		newCar.setVin("7896541230123");
    		newCar.setCarseat(carseat);
    		newCar.setCarbrand(brandd);
    		newCar.setPlateprovince(province);
			newCar.setCreatedby(employee);
			carRepository.saveAndFlush(newCar);

			//Data customer
			Customer newCustomer = new Customer();
			Job  work= jobRepository.findById(1);
    		Employee createdBy = employeeRepository.findById(1);
    		RegisType type = regisTypeRepository.findById(1);
    		LocalDateTime date = LocalDateTime.now();

    		newCustomer.setWork(work);
    		newCustomer.setName("name");
    		newCustomer.setAge(35);
    		newCustomer.setAddress("khonkean");
			newCustomer.setTel("0619613675");
			newCustomer.setCard_num("1234567890129"); 
    		newCustomer.setCreatedBy(createdBy);
    		newCustomer.setType(type);
			newCustomer.setDate(date);
			customerRepository.saveAndFlush(newCustomer);

			//Data rent car
			RentCar newRentCar = new RentCar();
			Customer rentalBy = customerRepository.findById(1);
        	Car car = carRepository.findById(1);
        	Employee createdby = employeeRepository.findById(1);
        	RentType types = rentTypeRepository.findById(1);
       		LocalDateTime rentDate = LocalDateTime.now();
        
        	newRentCar.setRentalBy(rentalBy);
        	newRentCar.setSelectcar(car);
       		newRentCar.setRentDate(rentDate);
        	newRentCar.setCreatedby(createdby);
        	newRentCar.setRentday(4);
        	newRentCar.setType(types);
        	newRentCar.setPrice(2400);
			rentcarRepository.saveAndFlush(newRentCar);
			
			//Data payment
			Payment newPayment = new Payment();
			Employee createby = employeeRepository.findById(1);
        	RentCar rent = rentcarRepository.findById(1);
        	PaymentOptions payoptions = optionsRepository.findById(1);
        	LocalDateTime paydate = LocalDateTime.now();

        	newPayment.setCreatedby(createby);
        	newPayment.setRent(rent);
       		newPayment.setPayoptions(payoptions);
        	newPayment.setDate(paydate);
			newPayment.setNote("จ่ายครบ");
			paymentRepository.save(newPayment);

			//Data return car
			ReturnsCar newReturnsCar = new ReturnsCar();
			RentCar rents = rentcarRepository.findById(1);
    		Employee create = employeeRepository.findById(1);
    		Payforfine pays = payforfineRepository.findById(1);
    		LocalDateTime returndate = LocalDateTime.now();

    		newReturnsCar.setRents(rents);
    		newReturnsCar.setCreatedby(create);
    		newReturnsCar.setPays(pays);
    		newReturnsCar.setNote("กระจกข้างเสียหาย");
    		newReturnsCar.setReturndate(returndate);
			returnsCarRepository.saveAndFlush(newReturnsCar);
			
			//Data maintenance
			Maintenance newMaintenance = new Maintenance();
			Repairman rm = repairmanRepository.findById(1);
    		Car c = carRepository.findById(1);
    		Service s = serviceRepository.findById(1);
    		LocalDateTime fixdate = LocalDateTime.now();
   
    		newMaintenance.setFixedBy(rm);
    		newMaintenance.setCar(c);
    		newMaintenance.setService(s);
    		newMaintenance.setMessageBox("เปลี่ยนยาง");
    		newMaintenance.setAutoPart("ยางหลังฝั่งซ้าย");
    		newMaintenance.setFixdate(fixdate);
    		newMaintenance.setMileage((long)863463);
    		maintenanceRepository.saveAndFlush(newMaintenance);

			
			provinceRepository.findAll().forEach(System.out::println);
			carseatRepository.findAll().forEach(System.out::println);
			brandRepository.findAll().forEach(System.out::println); 
			employeeRepository.findAll().forEach(System.out::println);
			payforfineRepository.findAll().forEach(System.out::println);
			rentTypeRepository.findAll().forEach(System.out::println);
			optionsRepository.findAll().forEach(System.out::println);
			repairmanRepository.findAll().forEach(System.out::println);
			serviceRepository.findAll().forEach(System.out::println);
		};
	}

}
