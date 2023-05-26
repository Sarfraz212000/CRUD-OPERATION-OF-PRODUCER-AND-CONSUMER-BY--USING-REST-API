package in.saffu.response;

import lombok.Data;

@Data
public class ContactResponse {
	
	private Integer id;
	private String name;
	private String email;
	private Long phno;

}
