package sg.edu.nus.iss.workshop14.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.iss.workshop14.Model.Contact;
import sg.edu.nus.iss.workshop14.Service.ContactsRedis;

@Controller
public class AddressBookController {

    @Autowired
    ContactsRedis service;

    //http://localhost:8080/
    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    //http://localhost:8080/contact/d7b626d8
    @GetMapping("/contact/{contactId}")
    public String getContact(Model model, @PathVariable(value = "contactId") String contactId) {
        Contact cc = service.findById(contactId);
        model.addAttribute("contact", cc);
        return "showContact";
    }

    //http://localhost:8080/contact/
    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, Model model) {
        Contact c = new Contact(
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber());
        service.save(c);
        model.addAttribute("contact", c);
        return "showContact";
    }
}
