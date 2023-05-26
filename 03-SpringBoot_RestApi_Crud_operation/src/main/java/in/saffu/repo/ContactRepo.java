package in.saffu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.saffu.entity.Contact;

public interface ContactRepo  extends JpaRepository<Contact, Integer>{

}
