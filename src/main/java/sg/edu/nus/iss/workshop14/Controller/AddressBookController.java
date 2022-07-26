package sg.edu.nus.iss.workshop14.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

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
    //@ModelAttribute cus need to show the values online
    @PostMapping("/contact")
    public String submitContact(@ModelAttribute Contact contact, Model model) {
        logger.info("contact id > " + contact.getId());
        service.save(contact);
        model.addAttribute("contact", contact);
        return "showContact";
    }
}
