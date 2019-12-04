package com.okta.Car.Carname.entity;
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
import com.okta.Car.FileSharing.entity.Employee;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@Entity
@NoArgsConstructor
@Table(name="CAR")
public class Car {

    @Id
    @SequenceGenerator(name="car_seq",sequenceName="car_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="car_seq")
    @Column(name = "CAR_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="VIN")
    private String vin;
    @Column(name="PLATE")
    private String plate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    private Brand carbrand;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Cartype.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Cartype cartype;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province plateprovince;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdby;


}