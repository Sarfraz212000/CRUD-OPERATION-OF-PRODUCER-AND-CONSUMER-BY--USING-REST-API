package in.saffu.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.saffu.entity.Contact;
import in.saffu.repo.ContactRepo;
import in.saffu.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepo repo;

	@Override
	public Contact upsert(Contact contact) {
		Contact save = repo.save(contact);
		return save;
	}

	@Override
	public List<Contact> getAllContact() {
		
		return repo.findAll();
	}

	@Override
	public Contact getById(Integer id) {
		
		Optional<Contact> findById = repo.findById(id);
		
		if (findById.isPresent()) {
			
			return findById.get();
			
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		
		if (repo.existsById(id)) {
			
			repo.deleteById(id);
			
			return " Delete sucess";
			
		}
		return "No record found";
	
	}

	
}
