package in.saffu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.saffu.entity.Contact;
import in.saffu.service.ContactService;
import in.saffu.serviceImpl.ContactServiceImpl;

@RestController
public class ContactRestController {

	@Autowired
	private ContactServiceImpl service;

	@PostMapping(value = "/contact" ,consumes = { "application/json" }, produces = { "application/json" })

	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
	 Contact upsert = service.upsert(contact);
		return new ResponseEntity<>(upsert, HttpStatus.CREATED);
	}

	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable Integer id) {
		Contact contact = service.getById(id);
		return new ResponseEntity<>(contact, HttpStatus.OK);

	}

	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContact() {
		List<Contact> allcourse = service.getAllContact();

		return new ResponseEntity<>(allcourse, HttpStatus.OK);
	}

	@GetMapping("/contacts/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
		String status = service.deleteById(id);
		return new ResponseEntity<String>(status,HttpStatus.OK);

	}

}
