package in.saffu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CONTACT_TAB")
public class Contact{
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private Long phno;

}
