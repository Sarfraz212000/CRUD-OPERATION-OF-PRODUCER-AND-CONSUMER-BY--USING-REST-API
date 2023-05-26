package in.saffu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.saffu.request.ContactRequest;
import in.saffu.response.ContactResponse;
import in.saffu.serviceImpl.ContactServiceImpl;

@Controller
public class ContactController {

	@Autowired
	private ContactServiceImpl serviceImpl;

	@Autowired
	private HttpSession session;

	@PostMapping("/contact")
	public String SaveData(ContactRequest contact, Model model) {
		Integer cid = (Integer) session.getAttribute("cid");
		if (cid != null) {
			serviceImpl.update(contact, cid);
			model.addAttribute("msg", "Data updated successfully");
			session.invalidate();
		} else {
			serviceImpl.upsert(contact);
			model.addAttribute("msg", "Data saved successfully");
		}

		
		model.addAttribute("contact", new ContactRequest());
		return "index";

	}

	@GetMapping("/")
	public String loadIndexPage(Model model) {
		model.addAttribute("contact", new ContactRequest());
		return "index";

	}

	@GetMapping("/display")
	public String loadDisplayPage(Model model) {
		model.addAttribute("contact", new ContactRequest());
		return "display";

	}

	@GetMapping("/allData")
	public String getAllData(ContactResponse response, Model model) {
		List<ContactResponse> allContact = serviceImpl.getAllContact();
		model.addAttribute("contact", allContact);
		return "getAllData";

	}

	@GetMapping("/delete")
	public String deleteById(@RequestParam Integer id) {
		serviceImpl.deleteById(id);
		return "redirect:/allData";

	}

	@GetMapping("/edit")
	public String Edit(@RequestParam("id") Integer id, Model model) {
		ContactResponse byId = serviceImpl.getById(id);
		session.setAttribute("cid", id);
		model.addAttribute("contact", byId);
		return "index";

	}

	@GetMapping("/id")
	public String getById(@RequestParam("id") Integer id, Model model) {
		ContactResponse byId = serviceImpl.getById(id);

		model.addAttribute("contact", byId);
		return "display";

	}

}
