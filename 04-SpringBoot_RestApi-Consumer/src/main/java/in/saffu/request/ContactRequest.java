package in.saffu.request;

import lombok.Data;

@Data
public class ContactRequest {
	
	private String name;
	private String email;
	private Long phno;

}
