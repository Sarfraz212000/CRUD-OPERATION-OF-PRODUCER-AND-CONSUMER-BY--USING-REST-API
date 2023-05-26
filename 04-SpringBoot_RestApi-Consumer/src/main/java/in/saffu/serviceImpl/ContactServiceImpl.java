package in.saffu.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import in.saffu.request.ContactRequest;
import in.saffu.response.ContactResponse;
import in.saffu.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	private String CREATE_CONTACT_URL = "http://localhost:9090/contact";

	private String GET_CONTACT_URL = "http://localhost:9090/contact/{id}";

	int port = 9090;
	String GETALL_CONTACT_URL = String.format("http://localhost:%d/contacts", port);

	private String DELETE_CONTACT_URL = "http://localhost:9090/contacts/{id}";

	@Override
	public List<ContactResponse> getAllContact() {
	    RestTemplate template = new RestTemplate();
	    
	    ResponseEntity<List<ContactResponse>> response = template.exchange(
	        GETALL_CONTACT_URL,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<List<ContactResponse>>() {}
	    );
	    List<ContactResponse> contacts = response.getBody();
	    return contacts;
	}


	@Override
	public ContactResponse getById(Integer id) {

		RestTemplate template = new RestTemplate();
		ResponseEntity<ContactResponse> entity = template.getForEntity(GET_CONTACT_URL, ContactResponse.class, id);
		ContactResponse body = entity.getBody();

		return body;
	}

	@Override
	public String deleteById(Integer id) {
	    WebClient webClient = WebClient.create();
	    webClient.get().uri(DELETE_CONTACT_URL, id).retrieve().toBodilessEntity().block();
	    return "success";
	}


	@Override
	public ContactResponse upsert(ContactRequest contact) {

		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<ContactResponse> entity = template.postForEntity(CREATE_CONTACT_URL, contact,
					ContactResponse.class);
			ContactResponse body = entity.getBody();
			return body;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	
	public ContactResponse update(ContactRequest contact,Integer id) {
		
		ContactResponse response=new ContactResponse();
		
		BeanUtils.copyProperties(contact, response);
		response.setId(id);

		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<ContactResponse> entity = template.postForEntity(CREATE_CONTACT_URL, response,
					ContactResponse.class);
			ContactResponse body = entity.getBody();
			return body;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public ContactResponse Update(ContactRequest contact, Integer id) {
		return null;
	}

}
