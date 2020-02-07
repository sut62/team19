package com.cpe.backend.Carname.Entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Collection;
import com.cpe.backend.FileSharing.Entity.Employee;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"VIN"})
    )
public class Car {

    @Id
    @SequenceGenerator(name="car_seq",sequenceName="car_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="car_seq")
    @Column(name = "CAR_ID", unique = true, nullable = true)
    private Long id;
    
    @NotNull
    @Size(min = 11,max = 17)
    @Column(name="VIN")
    private String vin;
    
    @Pattern(regexp = "[ก-ฮ][ก-ฮ]\\d{4}")
    @NotNull
    @Column(name="PLATE")
    private String plate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    @NotNull
    private Brand carbrand;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Carseat.class)
    @JoinColumn(name = "SEAT_ID", insertable = true)
    @NotNull
    private Carseat carseat;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    @NotNull
    private Province plateprovince;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    @NotNull
    private Employee createdby;


}