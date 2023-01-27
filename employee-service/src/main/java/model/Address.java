package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Setter
	@Getter
	private Long id;
	@Setter
	@Getter
	private String streetNo;
	@Setter
	@Getter
	private String city;
	@Setter
	@Getter
	private String state;
}
