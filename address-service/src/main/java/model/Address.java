package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Address {
	 @Id
	@Setter
	@Getter
	private Long id;
	@Setter
	@Getter
	@Column
	private String streetNo;
	@Setter
	@Getter
	@Column
	private String city;
	@Setter
	@Getter
	@Column
	private String state;

}
