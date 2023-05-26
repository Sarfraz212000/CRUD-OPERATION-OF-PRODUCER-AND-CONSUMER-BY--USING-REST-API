package in.saffu.service;

import java.util.List;

import in.saffu.request.ContactRequest;
import in.saffu.response.ContactResponse;

public interface ContactService {
	
	public List<ContactResponse> getAllContact();
	
	public ContactResponse getById(Integer id);
	
	public String deleteById(Integer id);
	
	public ContactResponse upsert(ContactRequest contact);
	
	public ContactResponse Update(ContactRequest contact,Integer id);
	
	

}
