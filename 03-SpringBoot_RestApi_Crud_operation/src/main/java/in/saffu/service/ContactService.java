package in.saffu.service;

import java.util.List;

import in.saffu.entity.Contact;

public interface ContactService {
	
	public Contact upsert(Contact contact);
	
	public List<Contact> getAllContact();
	
	public Contact getById(Integer id);
	
	public String deleteById(Integer id);

}
  