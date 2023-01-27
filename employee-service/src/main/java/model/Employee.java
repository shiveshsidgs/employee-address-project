package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Setter
		@Getter
	    private Long employeeId;
	    @Setter
		@Getter
	    private String firstName;
	    @Setter
		@Getter
	    private String lastName;
	    @Setter
		@Getter
	    private Long salary;
	    @Setter
		@Getter
	    private String email;
	    @Setter
		@Getter
	    private Long phoneNumber;
	    @Setter
		@Getter
	    //@JsonInclude()
	    @Transient
	    private Address address;
	    
}
